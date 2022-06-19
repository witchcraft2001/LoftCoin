package com.dmdev.loftcoin.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.dmdev.loftcoin.data.models.CmcCoin
import com.dmdev.loftcoin.data.models.Coin
import com.dmdev.loftcoin.data.models.CoinsQuery

interface CoinsRepo {
    @WorkerThread
    fun listings(currency: String) : List<CmcCoin>

    fun listings(query: CoinsQuery): LiveData<List<Coin>>
}
