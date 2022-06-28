package com.dmdev.loftcoin.di.components

import androidx.lifecycle.ViewModelProvider
import com.dmdev.loftcoin.di.modules.ViewModelModule
import com.dmdev.loftcoin.di.modules.WalletsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        WalletsModule::class,
        ViewModelModule::class
    ],
    dependencies = [
        BaseComponent::class
    ]
)
abstract class WalletsComponent {
    abstract fun viewModelFactory() : ViewModelProvider.Factory
}