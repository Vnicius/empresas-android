package io.github.vnicius.appempresas.data.repository.auth

import io.github.vnicius.appempresas.data.model.UserAuthData


class UserAuthRepository(
    private val remoteProvider: RemoteAuthProvider,
    private val localProvider: LocalAuthProvider
) : AuthRepository {

    override suspend fun auth(email: String, password: String): UserAuthData = remoteProvider.auth(email, password)

    override suspend fun saveAuthData(userAuthData: UserAuthData) = localProvider.saveAuthData(userAuthData)

    override fun loadAuthData(): UserAuthData? = localProvider.loadAuthData()
}