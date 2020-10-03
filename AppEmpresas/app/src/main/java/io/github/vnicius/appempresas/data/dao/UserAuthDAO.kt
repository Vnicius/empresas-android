package io.github.vnicius.appempresas.data.dao

import io.github.vnicius.appempresas.data.model.UserAuthData

/**
 * Interface to store and access user authentication data
 */
interface UserAuthDAO {

    /**
     * Get user authentication data
     */
    suspend fun getUserAuthData(): UserAuthData?

    /**
     * Save user authentication data
     */
    suspend fun saveUserAuthData(userAuthData: UserAuthData)
}