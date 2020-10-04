package io.github.vnicius.appempresas.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.vnicius.appempresas.data.auth.AuthException.InvalidEmailException
import io.github.vnicius.appempresas.data.auth.AuthException.InvalidPasswordException
import io.github.vnicius.appempresas.data.repository.auth.AuthRepository
import io.github.vnicius.appempresas.extension.isEmail
import io.github.vnicius.appempresas.util.RequestState
import io.github.vnicius.appempresas.util.RequestState.FAILED
import io.github.vnicius.appempresas.util.RequestState.SUCCESS
import kotlinx.coroutines.launch

class SignInViewModel(private val authRepository: AuthRepository): ViewModel() {

    private val mutableRequestState = MutableLiveData<RequestState>()

    var requestState: LiveData<RequestState> = mutableRequestState

    fun authenticate(email: String, password: String) {
        viewModelScope.launch {
            try {
                when {
                    (email.isEmpty() || !email.isEmail()) -> throw InvalidEmailException()
                    password.isEmpty() -> throw InvalidPasswordException()
                }

                mutableRequestState.postValue(RequestState.LOADING)

                val userData = authRepository.auth(email, password)
                authRepository.saveAuthData(userData)

                mutableRequestState.postValue(SUCCESS)
            } catch (e: Exception) {
                mutableRequestState.postValue(FAILED(e))
            }
        }
    }
}