package com.dmdev.loftcoin

import android.app.Application
import android.os.StrictMode

class LoftCoinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
        }
    }
}