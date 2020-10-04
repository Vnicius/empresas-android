package io.github.vnicius.appempresas.data.repository.auth

import io.github.vnicius.appempresas.data.auth.UserAuth
import io.github.vnicius.appempresas.data.model.UserAuthData


class RemoteUserAuth(private val userAuth: UserAuth): RemoteAuthProvider {

    override suspend fun auth(email: String, password: String): UserAuthData = userAuth.doAuth(email, password)
}