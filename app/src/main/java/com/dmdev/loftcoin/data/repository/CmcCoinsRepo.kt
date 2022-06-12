package com.dmdev.loftcoin.data.repository

import com.dmdev.loftcoin.data.api.CmcApiProvider
import com.dmdev.loftcoin.data.models.Coin
import java.io.IOException

class CmcCoinsRepo : CoinsRepo {
    private val api = CmcApiProvider.instance

    override fun listings(currency: String): List<Coin> {
        val result = api.listings(currency).execute()
        if (result.isSuccessful) {
            val items = result.body()
            return items?.data ?: throw IllegalStateException("Unknown server response")
        } else {
            val error = result.errorBody()
            throw IOException(error?.string() ?: "Unknown server response")
        }
    }
}