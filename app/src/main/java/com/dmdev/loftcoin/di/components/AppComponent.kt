package com.dmdev.loftcoin.di.components

import android.app.Application
import android.content.Context
import com.dmdev.loftcoin.di.modules.AppModule
import com.dmdev.loftcoin.di.modules.DataModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
    ]
)
abstract class AppComponent : BaseComponent {

    abstract override fun context(): Context

    @Component.Builder
    abstract class Builder {
        @BindsInstance
        abstract fun application(app: Application): Builder
        abstract fun build(): AppComponent
    }
}