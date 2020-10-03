package io.github.vnicius.appempresas.data.repository

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