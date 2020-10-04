package io.github.vnicius.appempresas.data.repository.auth

import io.github.vnicius.appempresas.data.model.UserAuthData


interface AuthRepository {

    /**
     * Perform authentication
     */
    suspend fun auth(email: String, password: String): UserAuthData

    /**
     * Save authentication data
     */
    suspend fun saveAuthData(userAuthData: UserAuthData)

    /**
     * Load current authentication data
     */
    fun loadAuthData(): UserAuthData?
}