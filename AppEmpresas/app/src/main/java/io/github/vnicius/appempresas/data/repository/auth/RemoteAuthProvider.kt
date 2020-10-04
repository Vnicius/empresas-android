package io.github.vnicius.appempresas.data.repository.auth

import io.github.vnicius.appempresas.data.model.UserAuthData

/**
 * Provider to perform the user authentication remotely
 */
interface RemoteAuthProvider {

    /**
     * Perform remote authentication
     */
    suspend fun auth(email: String, password: String): UserAuthData
}