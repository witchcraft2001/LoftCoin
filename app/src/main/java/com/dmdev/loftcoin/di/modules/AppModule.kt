package com.dmdev.loftcoin.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.dmdev.loftcoin.BuildConfig
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: Application): Context = app.applicationContext

    @Singleton
    @Provides
    fun providePreferences(app: Application): SharedPreferences = app.getSharedPreferences(
        BuildConfig.APPLICATION_ID,
        Context.MODE_PRIVATE
    )

    @Singleton
    @Provides
    fun provideIoExecutor() =
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2 + 1)
}