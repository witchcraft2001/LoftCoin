package com.dmdev.loftcoin.utils

interface PriceFormatter {
    fun format(value: Double): String
}