package io.github.vnicius.appempresas.data.repository

import io.github.vnicius.appempresas.data.model.UserAuthData


interface UserAuthRepository {

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
    suspend fun loadAuthData(): UserAuthData?
}