package com.dmdev.loftcoin.utils

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class PercentageFormatterTest {

    private lateinit var formatter: PercentageFormatter

    @Before
    fun setUp() {
        formatter = PercentageFormatter()
    }

    @Test
    fun string_contains_exact_three_fractional_digits() {
        assertEquals(formatter.format(1f.toDouble()), "1.000%")
        assertEquals(formatter.format(1.2344), "1.234%")
        assertEquals(formatter.format(1.2345), "1.235%")
    }
}