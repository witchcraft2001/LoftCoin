package com.dmdev.loftcoin.data.api

import com.dmdev.loftcoin.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request().newBuilder()
                .addHeader(CmcApi.API_KEY, BuildConfig.API_KEY)
                .build()
        )
}