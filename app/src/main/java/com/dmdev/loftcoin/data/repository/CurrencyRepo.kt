package com.dmdev.loftcoin.data.repository

import com.dmdev.loftcoin.data.models.Currency
import io.reactivex.Observable

interface CurrencyRepo {
    fun availableCurrencies() : Observable<List<Currency>>
    fun currency() : Observable<Currency>
    fun updateCurrency(currency: Currency)
}