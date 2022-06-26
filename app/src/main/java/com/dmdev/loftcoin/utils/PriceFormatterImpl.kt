package com.dmdev.loftcoin.utils

import com.dmdev.loftcoin.data.models.Currency
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class PriceFormatterImpl @Inject constructor() : PriceFormatter {

    override fun format(value: Double): String = NumberFormat.getCurrencyInstance().format(value)
    override fun format(value: Double, currency: Currency): String {
        if (!isCorrectCurrencyCode(currency.code))
            return format(value, currency.symbol)

        val formatter = getFormatter()
        formatter.currency = java.util.Currency.getInstance(currency.code)
        setFormatterCurrencySymbol(formatter, currency.symbol)
        return formatter.format(value)
    }

    override fun format(value: Double, currency: String): String {
        return String.format("%.2f %s", value, currency)
    }

    private fun setFormatterCurrencySymbol(formatter: DecimalFormat, currencySymbol: String) {
        val formatSymbols = formatter.decimalFormatSymbols
        formatSymbols.currencySymbol = currencySymbol
        formatter.decimalFormatSymbols = formatSymbols
    }

    private fun isCorrectCurrencyCode(currencyCode: String?): Boolean {
        if (currencyCode.isNullOrEmpty()) return false
        return try {
            java.util.Currency.getInstance(currencyCode)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun getFormatter(): DecimalFormat {
        return DecimalFormat.getCurrencyInstance(Locale.getDefault()) as DecimalFormat
    }
}