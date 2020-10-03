package io.github.vnicius.appempresas.data.repository

import io.github.vnicius.appempresas.data.dao.UserAuthDAO
import io.github.vnicius.appempresas.data.model.UserAuthData


class LocalUserAuth(private val userAuthDAO: UserAuthDAO): LocalAuthProvider {

    override suspend fun saveAuthData(userAuthData: UserAuthData) = userAuthDAO.saveUserAuthData(userAuthData)

    override suspend fun loadAuthData(): UserAuthData? = userAuthDAO.getUserAuthData()
}