package io.github.vnicius.appempresas.data.api.enterprise

import com.google.gson.GsonBuilder
import io.github.vnicius.appempresas.data.api.APIConstants
import io.github.vnicius.appempresas.data.dao.EnterpriseDAO
import io.github.vnicius.appempresas.data.model.Enterprise
import io.github.vnicius.appempresas.data.repository.auth.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class EnterpriseAPI(authRepository: AuthRepository) : EnterpriseDAO {

    private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val request =
            chain.request()
                .newBuilder().apply {
                    authRepository.loadAuthData()?.let { userAuthData ->
                        addHeader(APIConstants.ACCESS_TOKEN_KEY, userAuthData.accessToken)
                        addHeader(APIConstants.CLIENT_KEY, userAuthData.client)
                        addHeader(APIConstants.UID_KEY, userAuthData.uid)
                    }
                }
                .build()
        chain.proceed(request)
    }.build()
    private val enterpriseService: EnterpriseService = Retrofit.Builder()
        .baseUrl("${APIConstants.BASE_URL}/api/${APIConstants.API_VERSION}/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(httpClient)
        .build()
        .create(EnterpriseService::class.java)

    override suspend fun search(query: String): List<Enterprise> =
        withContext(Dispatchers.IO) {
            enterpriseService.search(query).enterprises.map {
                it.photo = "${APIConstants.BASE_URL}${it.photo}"
                it
            }
        }

    override suspend fun getEnterpriseById(id: Int): Enterprise? = withContext(Dispatchers.IO) {
        enterpriseService.show(id)
    }
}