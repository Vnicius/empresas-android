package io.github.vnicius.appempresas.data.model


data class UserAuthData(
    val accessToken: String,
    val client: String,
    val uid: String,
    val expiry: Long
)