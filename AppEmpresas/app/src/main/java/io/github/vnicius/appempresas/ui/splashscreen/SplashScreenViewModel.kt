package io.github.vnicius.appempresas.ui.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.vnicius.appempresas.data.repository.auth.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SplashScreenViewModel(private val authRepository: AuthRepository) : ViewModel() {

    fun checkCurrentAuthentication(onSuccess: () -> Unit, onFailure: () -> Unit) {
        viewModelScope.launch {
            val expiryTime = authRepository.loadAuthData()?.expiry ?: 0
            val currentTime = System.currentTimeMillis()

            with(Dispatchers.Main) {
                if (expiryTime < currentTime) {
                    onFailure()
                } else {
                    onSuccess()
                }
            }
        }
    }
}