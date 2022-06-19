package com.dmdev.loftcoin.di.modules

import com.dmdev.loftcoin.BuildConfig
import com.dmdev.loftcoin.data.api.ApiKeyInterceptor
import com.dmdev.loftcoin.data.api.CmcApi
import com.dmdev.loftcoin.data.models.Coin
import com.dmdev.loftcoin.data.models.Listings
import com.dmdev.loftcoin.data.repository.CmcCoinsRepo
import com.dmdev.loftcoin.data.repository.CoinsRepo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Binds
    abstract fun bindRepo(repo: CmcCoinsRepo) : CoinsRepo

    @Module
    companion object {
        @Provides
        @Singleton
        fun getOkHttpClient(): OkHttpClient {
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

        @Provides
        fun getMoshi(): Moshi {
            val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            return moshi.newBuilder()
                .add(Coin::class.java, moshi.adapter(Coin::class.java))
                .add(Listings::class.java, moshi.adapter(Listings::class.java))
                .build()
        }

        @Provides
        fun getRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit =
            Retrofit.Builder()
                .apply {
                    client(okHttpClient)
                    baseUrl(BuildConfig.API_ENDPOINT)
                    addConverterFactory(MoshiConverterFactory.create(moshi))
                }
                .build()

        @Provides
        fun getCmcApi(retrofit: Retrofit): CmcApi = retrofit.create(CmcApi::class.java)
    }

}