package com.dmdev.loftcoin.di.components

import androidx.lifecycle.ViewModelProvider
import com.dmdev.loftcoin.di.modules.RatesModule
import com.dmdev.loftcoin.di.modules.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RatesModule::class,
        ViewModelModule::class
    ],
    dependencies = [
        BaseComponent::class
    ]
)
abstract class RatesComponent {
    abstract fun viewModelFactory() : ViewModelProvider.Factory
}