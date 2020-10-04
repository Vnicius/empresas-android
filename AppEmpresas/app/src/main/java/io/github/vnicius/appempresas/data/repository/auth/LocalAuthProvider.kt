package io.github.vnicius.appempresas.data.repository.auth

import io.github.vnicius.appempresas.data.model.UserAuthData

/**
 * Interface to provide access to the user authentication data stored locally
 */
interface LocalAuthProvider {

    /**
     * Save authentication data locally
     */
    fun saveAuthData(userAuthData: UserAuthData)

    /**
     * Load local authentication data
     */
    fun loadAuthData(): UserAuthData?
}