package io.github.vnicius.appempresas.data.dao

import io.github.vnicius.appempresas.data.model.UserAuthData

/**
 * Interface to store and access user authentication data
 */
interface UserAuthDAO {

    /**
     * Get user authentication data
     */
    fun getUserAuthData(): UserAuthData?

    /**
     * Save user authentication data
     */
    fun saveUserAuthData(userAuthData: UserAuthData)
}