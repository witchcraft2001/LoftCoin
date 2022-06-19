package com.dmdev.loftcoin.data.repository

import com.dmdev.loftcoin.data.api.CmcApi
import com.dmdev.loftcoin.data.models.Coin
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CmcCoinsRepo @Inject constructor(private val api: CmcApi) : CoinsRepo {

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