package io.github.vnicius.appempresas.data.repository

import io.github.vnicius.appempresas.data.model.UserAuthData


interface LocalAuthProvider {

    suspend fun saveAuthData(userAuthData: UserAuthData)

    suspend fun loadAuthData(): UserAuthData?
}