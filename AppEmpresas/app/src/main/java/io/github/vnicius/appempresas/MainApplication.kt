package io.github.vnicius.appempresas

import android.app.Application
import io.github.vnicius.appempresas.di.dataModule
import io.github.vnicius.appempresas.di.signInModule
import io.github.vnicius.appempresas.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(dataModule, splashModule, signInModule)
        }
    }
}