package com.dmdev.loftcoin.di.modules

import android.content.Context
import androidx.room.Room
import com.dmdev.loftcoin.BuildConfig
import com.dmdev.loftcoin.data.api.ApiKeyInterceptor
import com.dmdev.loftcoin.data.api.CmcApi
import com.dmdev.loftcoin.data.models.CmcCoin
import com.dmdev.loftcoin.data.models.Listings
import com.dmdev.loftcoin.data.repository.*
import com.dmdev.loftcoin.data.room.LoftCoinDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Binds
    abstract fun bindRepo(repo: CmcCoinsRepo) : CoinsRepo

    @Binds
    abstract fun bindCurrencyRepo(repo: CurrencyRepoImpl) : CurrencyRepo

    @Binds
    abstract fun bindWalletsRepo(repo: WalletsRepoImpl) : WalletsRepo

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
                .add(CmcCoin::class.java, moshi.adapter(CmcCoin::class.java))
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
                    addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                }
                .build()

        @Provides
        fun getCmcApi(retrofit: Retrofit): CmcApi = retrofit.create(CmcApi::class.java)

        @Provides
        @Singleton
        fun getDatabase(context: Context) =
            Room.databaseBuilder(context, LoftCoinDatabase::class.java, "loftcoins.db").build()
    }

}