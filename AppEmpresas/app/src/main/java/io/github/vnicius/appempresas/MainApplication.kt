package io.github.vnicius.appempresas

import android.app.Application
import io.github.vnicius.appempresas.di.*
import io.github.vnicius.internetchecker.InternetChecker
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        InternetChecker.init(applicationContext)

        startKoin {
            androidContext(this@MainApplication)
            modules(dataModule, splashModule, signInModule, searchModule, enterpriseDetailsModule)
        }
    }
}