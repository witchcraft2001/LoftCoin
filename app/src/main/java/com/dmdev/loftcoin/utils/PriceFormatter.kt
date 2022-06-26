package com.dmdev.loftcoin.utils

import com.dmdev.loftcoin.data.models.Currency

interface PriceFormatter {
    fun format(value: Double): String
    fun format(value: Double, currency: Currency): String
    fun format(value: Double, currency: String): String
}