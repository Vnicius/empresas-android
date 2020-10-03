package io.github.vnicius.appempresas.data.api.auth

import io.github.vnicius.appempresas.data.model.AuthBodyRequest
import io.github.vnicius.appempresas.data.model.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Service of user authentication
 */
interface UserAuthService {

    @POST("users/auth/sign_in")
    suspend fun signIn(@Body bodyRequest: AuthBodyRequest): Response<AuthResponse>
}