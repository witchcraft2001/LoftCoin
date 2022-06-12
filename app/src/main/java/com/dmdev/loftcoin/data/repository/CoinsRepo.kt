package com.dmdev.loftcoin.data.repository

import androidx.annotation.WorkerThread
import com.dmdev.loftcoin.data.models.Coin

interface CoinsRepo {
    @WorkerThread
    fun listings(currency: String) : List<Coin>
}
