package com.dmdev.loftcoin.data.api

import com.dmdev.loftcoin.BuildConfig
import com.dmdev.loftcoin.data.models.Coin
import com.dmdev.loftcoin.data.models.Listings
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object CmcApiProvider {
    val instance by lazy { createRetrofit().create(CmcApi::class.java) }

    private fun createRetrofit(): Retrofit {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .apply {
                client(getOkHttpClient())
                baseUrl(BuildConfig.API_ENDPOINT)
                addConverterFactory(MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(Coin::class.java, moshi.adapter(Coin::class.java))
                        .add(Listings::class.java, moshi.adapter(Listings::class.java))
                        .build()
                ))
            }
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            addInterceptor { chain -> ApiKeyInterceptor.intercept(chain) }
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                    redactHeader(CmcApi.API_KEY)
                })
            }
        }
        return builder.build()
    }
}