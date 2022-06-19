package com.dmdev.loftcoin.di.modules

import androidx.lifecycle.ViewModel
import com.dmdev.loftcoin.di.ViewModelKey
import com.dmdev.loftcoin.ui.rates.RatesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RatesModule {

    @Binds
    @IntoMap
    @ViewModelKey(RatesViewModel::class)
    abstract fun ratesViewModel(impl: RatesViewModel): ViewModel
}
