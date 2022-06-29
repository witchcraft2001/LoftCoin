package com.dmdev.loftcoin.utils

import com.dmdev.loftcoin.data.models.Currency
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import java.text.NumberFormat
import java.util.*

class PriceFormatterImplTest {

    private lateinit var formatter: PriceFormatter

    @Before
    fun setUp() {
        formatter = PriceFormatterImpl()
    }

    @Test
    fun format_with_currency_code_BTC() {
        assertEquals(formatter.format(1.23, "BTC"), "1,23 BTC")
    }

    @Test
    fun format_with_currency_EUR() {
        assertEquals(
            formatter.format(1.23, Currency("EUR", "€", "Euro")),
            NumberFormat.getCurrencyInstance(Locale.GERMANY).format(1.23)
        )
    }

    @Test
    fun format_with_currency_RUB() {
        assertEquals(
            formatter.format(1.23, Currency("RUB", "₽", "Ruble")),
            NumberFormat.getCurrencyInstance(Locale("ru", "RU")).format(1.23)
        )
    }

    @Test
    fun format_without_currency() {
        assertEquals(formatter.format(1.23), NumberFormat.getCurrencyInstance().format(1.23))
    }
}