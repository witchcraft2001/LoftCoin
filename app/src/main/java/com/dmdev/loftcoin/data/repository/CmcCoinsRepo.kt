package com.dmdev.loftcoin.data.repository

import com.dmdev.loftcoin.BuildConfig
import com.dmdev.loftcoin.data.api.ApiKeyInterceptor
import com.dmdev.loftcoin.data.api.CmcApi
import com.dmdev.loftcoin.data.models.Coin
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CmcCoinsRepo : CoinsRepo {
    companion object {
        fun createRetrofit(): Retrofit {
            return Retrofit.Builder()
                .apply {
                    client(getOkHttpClient())
                    baseUrl(BuildConfig.API_ENDPOINT)
                    addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
                }
                .build()
        }

        private fun getOkHttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder().apply {
                addInterceptor { chain -> ApiKeyInterceptor.intercept(chain) }
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.HEADERS)
                        redactHeader(CmcApi.API_KEY)
                    })
                }
            }
            return builder.build()
        }
    }

    override fun listings(currency: String): List<Coin> {
        TODO("Not yet implemented")
    }
}