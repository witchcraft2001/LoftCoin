package com.dmdev.loftcoin.di.components

import android.content.Context
import com.dmdev.loftcoin.data.repository.CoinsRepo
import com.dmdev.loftcoin.data.repository.CurrencyRepo
import com.dmdev.loftcoin.data.repository.WalletsRepo

interface BaseComponent {
    fun context(): Context
    fun coinsRepo(): CoinsRepo
    fun currencyRepo(): CurrencyRepo
    fun walletsRepo(): WalletsRepo
}