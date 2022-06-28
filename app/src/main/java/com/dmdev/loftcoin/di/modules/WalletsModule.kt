package com.dmdev.loftcoin.di.modules

import androidx.lifecycle.ViewModel
import com.dmdev.loftcoin.di.ViewModelKey
import com.dmdev.loftcoin.ui.wallets.WalletsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WalletsModule {

    @Binds
    @IntoMap
    @ViewModelKey(WalletsViewModel::class)
    abstract fun walletsViewModel(impl: WalletsViewModel): ViewModel
}
