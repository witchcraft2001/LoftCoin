package com.dmdev.loftcoin.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.dmdev.loftcoin.R
import com.dmdev.loftcoin.data.models.Currency
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CurrencyRepoImpl @Inject constructor(
    context: Context,
    private val prefereces: SharedPreferences
) : CurrencyRepo {
    companion object {
        private const val KEY_CURRENCY = "KEY_CURRENCY"
    }

    private val currencies: HashMap<String, Currency> by lazy {
        hashMapOf(
            "USD" to Currency("USD", "$", context.getString(R.string.usd)),
            "EUR" to Currency("EUR", "€", context.getString(R.string.eur)),
            "RUB" to Currency("RUB", "₽", context.getString(R.string.rub))
        )
    }


    override fun availableCurrencies() = Observable.just(currencies.values.toList())


    override fun currency(): Observable<Currency> {
        return Observable.create { emitter ->
            val listener = SharedPreferences.OnSharedPreferenceChangeListener { prefs, key ->
                if (!emitter.isDisposed) {
                    emitter.onNext(
                        currencies[prefs.getString(key, currencies.keys.first())]
                            ?: currencies.values.first()
                    )
                }
            }
            prefereces.registerOnSharedPreferenceChangeListener(listener)
            emitter.setCancellable { prefereces.unregisterOnSharedPreferenceChangeListener(listener) }
            emitter.onNext(
                currencies[prefereces.getString(KEY_CURRENCY, currencies.keys.first())]
                    ?: currencies.values.first()
            )
        }
    }

    override fun updateCurrency(currency: Currency) {
        prefereces.edit().putString(KEY_CURRENCY, currency.code).apply()
    }
}