package com.dmdev.loftcoin.data.repository

import com.dmdev.loftcoin.data.models.Coin
import com.dmdev.loftcoin.data.models.CoinsQuery
import io.reactivex.Observable

interface CoinsRepo {
    fun listings(query: CoinsQuery): Observable<List<Coin>>
}
