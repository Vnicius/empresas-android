package io.github.vnicius.appempresas.di

import io.github.vnicius.appempresas.data.api.auth.UserAuthAPI
import io.github.vnicius.appempresas.data.repository.AuthRepository
import io.github.vnicius.appempresas.data.repository.LocalUserAuth
import io.github.vnicius.appempresas.data.repository.RemoteUserAuth
import io.github.vnicius.appempresas.data.sharedpreferences.UserSharedPreferences
import io.github.vnicius.appempresas.ui.signin.SignInViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { UserSharedPreferences(androidContext()) }
    single { UserAuthAPI() }
    single { LocalUserAuth(get()) }
    single { RemoteUserAuth(get()) }
    single { AuthRepository(get(), get()) }
}

val signInModule = module {
    viewModel { SignInViewModel(get()) }
}