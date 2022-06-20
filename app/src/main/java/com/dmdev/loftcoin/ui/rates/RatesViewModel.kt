package com.dmdev.loftcoin.ui.rates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dmdev.loftcoin.data.models.CmcCoin
import com.dmdev.loftcoin.data.models.Coin
import com.dmdev.loftcoin.data.models.CoinsQuery
import com.dmdev.loftcoin.data.repository.CoinsRepo
import java.util.concurrent.Executors
import java.util.concurrent.Future
import javax.inject.Inject

class RatesViewModel @Inject constructor(coinsRepo: CoinsRepo): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val forceRefresh = MutableLiveData(true)

    val coins: LiveData<List<Coin>>

    init {
        refresh()
        val query = Transformations.map(forceRefresh) {
            _isLoading.postValue(true)
            CoinsQuery("USD", it)
        }
        val coins = Transformations.switchMap(query) {
            coinsRepo.listings(it)
        }
        this.coins = Transformations.map(coins) {
            _isLoading.postValue(false)
            it
        }
    }

    fun refresh() {
        forceRefresh.postValue(true)
    }
}