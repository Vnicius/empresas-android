package io.github.vnicius.appempresas.data.auth

import io.github.vnicius.appempresas.data.model.UserAuthData

/**
 * Interface to perform user authentication
 */
interface UserAuth {

    /**
     * Perform the authentication of the user
     *
     * @param email of the user
     * @param password of the user
     *
     * @return the user authentication data
     */
    suspend fun doAuth(email: String, password: String): UserAuthData
}