package com.dmdev.loftcoin.di.modules

import com.dmdev.loftcoin.utils.PriceFormatter
import com.dmdev.loftcoin.utils.PriceFormatterImpl
import dagger.Binds
import dagger.Module

@Module
interface UtilsModule {
    @Binds
    fun getPriceFormatter(impl: PriceFormatterImpl) :PriceFormatter
}