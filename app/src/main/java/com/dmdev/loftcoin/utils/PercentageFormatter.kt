package com.dmdev.loftcoin.utils

import java.util.*
import javax.inject.Inject

class PercentageFormatter @Inject constructor() {
    fun format(value: Double) : String = String.format(Locale.US, "%.3f%%", value)
}