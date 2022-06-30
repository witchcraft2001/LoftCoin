package com.dmdev.loftcoin.data

import android.content.Context
import android.content.SharedPreferences
import com.dmdev.loftcoin.data.repository.CurrencyRepoImpl

class FakeCurrencyRepo(
    context: Context,
    sharedPreferences: SharedPreferences
) : CurrencyRepoImpl(context, sharedPreferences)
