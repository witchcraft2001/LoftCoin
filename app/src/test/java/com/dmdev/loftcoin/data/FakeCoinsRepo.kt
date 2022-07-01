package com.dmdev.loftcoin.data

import com.dmdev.loftcoin.data.models.Coin
import com.dmdev.loftcoin.data.models.CoinsQuery
import com.dmdev.loftcoin.data.models.Currency
import com.dmdev.loftcoin.data.repository.CoinsRepo
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

class FakeCoinsRepo: CoinsRepo {
    val listings = PublishSubject.create<List<Coin>>()

    override fun listings(query: CoinsQuery): Observable<List<Coin>> = listings

    override fun coin(currency: Currency, id: Long): Single<Coin> {
        TODO("Not yet implemented")
    }
}