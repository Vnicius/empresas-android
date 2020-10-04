package io.github.vnicius.appempresas.di

import io.github.vnicius.appempresas.data.api.auth.UserAuthAPI
import io.github.vnicius.appempresas.data.api.enterprise.EnterpriseAPI
import io.github.vnicius.appempresas.data.auth.UserAuth
import io.github.vnicius.appempresas.data.dao.EnterpriseDAO
import io.github.vnicius.appempresas.data.dao.UserAuthDAO
import io.github.vnicius.appempresas.data.repository.auth.*
import io.github.vnicius.appempresas.data.repository.enterprise.EnterpriseProvider
import io.github.vnicius.appempresas.data.repository.enterprise.EnterpriseRepository
import io.github.vnicius.appempresas.data.repository.enterprise.EnterprisesRepository
import io.github.vnicius.appempresas.data.repository.enterprise.RemoteEnterpriseProvider
import io.github.vnicius.appempresas.data.sharedpreferences.UserSharedPreferences
import io.github.vnicius.appempresas.ui.signin.SignInViewModel
import io.github.vnicius.appempresas.ui.splashscreen.SplashScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<UserAuthDAO> { UserSharedPreferences(androidContext()) }
    single<UserAuth> { UserAuthAPI() }
    single<EnterpriseDAO> { EnterpriseAPI(get()) }

    single<LocalAuthProvider> { LocalUserAuth(get()) }
    single<RemoteAuthProvider> { RemoteUserAuth(get()) }
    single<RemoteEnterpriseProvider> { EnterpriseProvider(get()) }

    single<AuthRepository> { UserAuthRepository(get(), get()) }
    single<EnterpriseRepository> { EnterprisesRepository(get()) }
}

val splashModule = module {
    viewModel { SplashScreenViewModel(get()) }
}

val signInModule = module {
    viewModel { SignInViewModel(get()) }
}