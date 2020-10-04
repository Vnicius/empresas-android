package io.github.vnicius.appempresas.data.auth

/**
 * Exceptions for authentication operations
 */
sealed class AuthException(override val message: String?): Exception(message) {

    class InvalidCredentialsException(message: String?): AuthException(message)

    class DefaultAuthException(message: String? = null): AuthException(message)

    class InvalidEmailException(message: String? = null): AuthException(message)

    class InvalidPasswordException(message: String? = null): AuthException(message)
}