package io.github.vnicius.appempresas.data.repository

import io.github.vnicius.appempresas.data.model.UserAuthData

/**
 * Interface to provide access to the user authentication data stored locally
 */
interface LocalAuthProvider {

    /**
     * Save authentication data locally
     */
    suspend fun saveAuthData(userAuthData: UserAuthData)

    /**
     * Load local authentication data
     */
    suspend fun loadAuthData(): UserAuthData?
}