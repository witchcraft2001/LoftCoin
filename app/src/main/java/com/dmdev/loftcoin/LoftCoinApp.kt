package com.dmdev.loftcoin

import android.app.Application
import android.os.StrictMode
import com.dmdev.loftcoin.di.components.BaseComponent
import com.dmdev.loftcoin.di.components.DaggerAppComponent

class LoftCoinApp : Application() {

    private lateinit var _component: BaseComponent

    val component: BaseComponent
        get() = _component

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
        }
        _component = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}