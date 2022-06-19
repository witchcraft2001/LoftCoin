package com.dmdev.loftcoin.di

import android.content.Context
import com.dmdev.loftcoin.data.repository.CoinsRepo

interface BaseComponent {
    fun context() : Context
    fun coinsRepo() : CoinsRepo
}