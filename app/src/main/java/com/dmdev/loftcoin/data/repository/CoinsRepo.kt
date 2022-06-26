package com.dmdev.loftcoin.data.repository

import com.dmdev.loftcoin.data.models.Coin
import com.dmdev.loftcoin.data.models.CoinsQuery
import com.dmdev.loftcoin.data.models.Currency
import io.reactivex.Observable
import io.reactivex.Single

interface CoinsRepo {
    fun listings(query: CoinsQuery): Observable<List<Coin>>
    fun coin(currency: Currency, id: Long): Single<Coin>
}
