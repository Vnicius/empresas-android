package io.github.vnicius.appempresas.data.api.auth

import com.google.gson.GsonBuilder
import io.github.vnicius.appempresas.data.api.APIConstants
import io.github.vnicius.appempresas.data.auth.AuthException
import io.github.vnicius.appempresas.data.auth.UserAuth
import io.github.vnicius.appempresas.data.model.AuthBodyRequest
import io.github.vnicius.appempresas.data.model.UserAuthData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Perform user authentication using the default API
 */
class UserAuthAPI : UserAuth {

    private val userAuthService: UserAuthService = Retrofit.Builder()
        .baseUrl("${APIConstants.BASE_URL}/api/${APIConstants.API_VERSION}/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(UserAuthService::class.java)

    override suspend fun doAuth(email: String, password: String): UserAuthData =
        withContext(Dispatchers.IO) {
            val response = userAuthService.signIn(AuthBodyRequest(email, password))
            val authResponse = response.body() ?: run {
                throw AuthException.InvalidCredentialsException("Unauthorized")
            }

            if (authResponse.success) {
                val accessToken = response.headers().get(APIConstants.ACCESS_TOKEN_KEY) ?: run {
                    throw AuthException.DefaultAuthException()
                }
                val uid = response.headers().get(APIConstants.UID_KEY) ?: run {
                    throw AuthException.DefaultAuthException()
                }
                val client = response.headers().get(APIConstants.CLIENT_KEY) ?: run {
                    throw AuthException.DefaultAuthException()
                }
                val expiry = response.headers().get(APIConstants.EXPIRY_KEY)?.toLong() ?: run {
                    throw AuthException.DefaultAuthException()
                }

                UserAuthData(accessToken, client, uid, expiry)
            } else {
                throw AuthException.InvalidCredentialsException(authResponse.errors?.firstOrNull())
            }
        }
}