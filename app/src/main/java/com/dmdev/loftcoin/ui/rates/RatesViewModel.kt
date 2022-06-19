package com.dmdev.loftcoin.ui.rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmdev.loftcoin.data.models.Coin
import com.dmdev.loftcoin.data.repository.CoinsRepo
import java.util.concurrent.Executors
import java.util.concurrent.Future
import javax.inject.Inject

class RatesViewModel @Inject constructor(private val ratesRepo: CoinsRepo): ViewModel() {

    private val executor = Executors.newSingleThreadExecutor()
    private var future: Future<*>? = null

    private val _listings = MutableLiveData<List<Coin>>()
    val listings: LiveData<List<Coin>> = _listings

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        refresh()
    }

    fun refresh() {
        _isLoading.postValue(true)
        future = executor.submit {
            try {
                val listings = ratesRepo.listings("USD")
                _listings.postValue(listings)
            } catch (e: Throwable) {
                e.printStackTrace()
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    override fun onCleared() {
        future?.cancel(true)
        super.onCleared()
    }
}