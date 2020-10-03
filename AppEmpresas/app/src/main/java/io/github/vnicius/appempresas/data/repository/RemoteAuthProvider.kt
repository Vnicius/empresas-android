package io.github.vnicius.appempresas.data.repository

import io.github.vnicius.appempresas.data.model.UserAuthData


interface RemoteAuthProvider {

    suspend fun auth(email: String, password: String): UserAuthData
}