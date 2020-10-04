package io.github.vnicius.appempresas.data.repository.auth

import io.github.vnicius.appempresas.data.dao.UserAuthDAO
import io.github.vnicius.appempresas.data.model.UserAuthData


class LocalUserAuth(private val userAuthDAO: UserAuthDAO): LocalAuthProvider {

    override fun saveAuthData(userAuthData: UserAuthData) = userAuthDAO.saveUserAuthData(userAuthData)

    override fun loadAuthData(): UserAuthData? = userAuthDAO.getUserAuthData()
}