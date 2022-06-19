package com.dmdev.loftcoin.di.modules

import androidx.lifecycle.ViewModelProvider
import com.dmdev.loftcoin.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun viewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory
}