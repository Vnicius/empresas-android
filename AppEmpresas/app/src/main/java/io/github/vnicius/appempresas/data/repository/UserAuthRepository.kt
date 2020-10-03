package io.github.vnicius.appempresas.data.repository

import io.github.vnicius.appempresas.data.model.UserAuthData


interface UserAuthRepository {

    suspend fun auth(email: String, password: String): UserAuthData

    suspend fun saveAuthData(userAuthData: UserAuthData)

    suspend fun loadAuthData(): UserAuthData?
}