package com.dmdev.loftcoin.ui.rates

import androidx.lifecycle.ViewModel
import com.dmdev.loftcoin.data.models.Coin
import com.dmdev.loftcoin.data.models.CoinsQuery
import com.dmdev.loftcoin.data.models.SortBy
import com.dmdev.loftcoin.data.repository.CoinsRepo
import com.dmdev.loftcoin.data.repository.CurrencyRepo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class RatesViewModel @Inject constructor(
    coinsRepo: CoinsRepo,
    currencyRepo: CurrencyRepo
) : ViewModel() {
    companion object {
        private const val DEFAULT_CURRENCY = "USD"
    }

    private val pullToRefresh = BehaviorSubject.createDefault(Any::class)
    private val sortBy = BehaviorSubject.createDefault(SortBy.RANK)
    private val forceUpdate = AtomicBoolean()
    private val isLoading = BehaviorSubject.create<Boolean>()

    private val coins: Observable<List<Coin>> = pullToRefresh.map { CoinsQuery(DEFAULT_CURRENCY) }
        .switchMap { query -> currencyRepo.currency().map { query.copy(currency = it.code) } }
        .doOnNext { forceUpdate.set(true) }
        .doOnNext { isLoading.onNext(true) }
        .switchMap { query -> sortBy.map { query.copy(sortBy = it) } }
        .map { query -> query.copy(forceUpdate = forceUpdate.getAndSet(false)) }
        .switchMap { query -> coinsRepo.listings(query) }
        .doOnEach { isLoading.onNext(false) }

    fun getCoins(): Observable<List<Coin>> = coins.observeOn(AndroidSchedulers.mainThread())

    fun isLoading(): Observable<Boolean> = isLoading.observeOn(AndroidSchedulers.mainThread())

    fun refresh() {
        pullToRefresh.onNext(Any::class)
    }

    fun switchOrder() {
        sortBy.onNext(SortBy.values()[(SortBy.values().indexOf(sortBy.value) + 1) % SortBy.values().size])
    }
}