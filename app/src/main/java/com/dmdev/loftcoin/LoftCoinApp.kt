package com.dmdev.loftcoin

import android.app.Application
import android.os.StrictMode
import android.util.Log
import com.dmdev.loftcoin.di.components.BaseComponent
import com.dmdev.loftcoin.di.components.DaggerAppComponent
import timber.log.Timber

class LoftCoinApp : Application() {

    private lateinit var _component: BaseComponent

    val component: BaseComponent
        get() = _component

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
        }
        initTimber()
        _component = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    private fun initTimber() {
        Timber.plant(if (BuildConfig.DEBUG) {
            Timber.DebugTree()
        } else {
            object: Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    // todo: add remote logs
                }
            }
        })
    }
}