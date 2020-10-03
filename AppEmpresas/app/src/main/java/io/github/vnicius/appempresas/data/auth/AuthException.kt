package io.github.vnicius.appempresas.data.auth

sealed class AuthException(override val message: String?): Exception(message) {

    class InvalidCredentialsException(message: String?): AuthException(message)

    class DefaultAuthException(message: String? = null): AuthException(message)
}