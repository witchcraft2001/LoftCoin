package com.dmdev.loftcoin.di.modules

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.dmdev.loftcoin.di.FragmentKey
import com.dmdev.loftcoin.ui.converter.ConverterFragment
import com.dmdev.loftcoin.ui.rates.RatesFragment
import com.dmdev.loftcoin.ui.wallets.WalletsFragment
import com.dmdev.loftcoin.utils.LoftFragmentFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    abstract fun fragmentFactory(impl: LoftFragmentFactory): FragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(RatesFragment::class)
    abstract fun ratesFragment(impl: RatesFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ConverterFragment::class)
    abstract fun converterFragment(impl: ConverterFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(WalletsFragment::class)
    abstract fun walletsFragment(impl: WalletsFragment): Fragment
}