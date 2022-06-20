package com.dmdev.loftcoin.utils

import java.text.NumberFormat
import javax.inject.Inject

class PriceFormatterImpl @Inject constructor(): PriceFormatter {
    override fun format(value: Double): String = NumberFormat.getCurrencyInstance().format(value)
}