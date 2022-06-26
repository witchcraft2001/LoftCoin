package com.dmdev.loftcoin.di.components

import android.content.Context
import com.dmdev.loftcoin.data.repository.CoinsRepo
import com.dmdev.loftcoin.data.repository.CurrencyRepo

interface BaseComponent {
    fun context() : Context
    fun coinsRepo() : CoinsRepo
    fun currencyRepo() : CurrencyRepo
}