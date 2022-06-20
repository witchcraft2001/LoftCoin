package com.dmdev.loftcoin.di.components

import android.content.Context
import com.dmdev.loftcoin.data.repository.CoinsRepo

interface BaseComponent {
    fun context() : Context
    fun coinsRepo() : CoinsRepo
}