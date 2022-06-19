package com.dmdev.loftcoin.di

import com.dmdev.loftcoin.di.modules.MainModule
import com.dmdev.loftcoin.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MainModule::class
    ],
    dependencies = [
        BaseComponent::class
    ]
)
abstract class MainComponent {

    abstract fun inject(activity: MainActivity)
}