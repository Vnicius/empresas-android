package io.github.vnicius.appempresas.data.dao

import io.github.vnicius.appempresas.data.model.UserAuthData


interface UserAuthDAO {

    suspend fun getUserAuthData(): UserAuthData?

    suspend fun saveUserAuthData(userAuthData: UserAuthData)
}