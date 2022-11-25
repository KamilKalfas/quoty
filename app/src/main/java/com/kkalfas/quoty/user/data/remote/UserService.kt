package com.kkalfas.quoty.user.data.remote

import com.kkalfas.quoty.BuildConfig
import com.kkalfas.quoty.network.NetworkClientProvider
import com.kkalfas.quoty.network.ktor.authHeader
import com.kkalfas.quoty.network.ktor.userTokenHeader
import com.kkalfas.quoty.user.data.model.GetUserResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

private const val HOST = "favqs.com"
private const val PATH_GET = "/api/users"

interface UserService {

    suspend fun getUser(token: String, login: String) : GetUserResponse

    class Impl @Inject constructor(
        clientProvider: NetworkClientProvider<HttpClient>,
    ) : UserService {

        private val client by lazy { clientProvider.client }

        override suspend fun getUser(token: String, login: String): GetUserResponse {
            val response = client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = HOST
                    path(PATH_GET, login)
                }
                authHeader(BuildConfig.API_KEY)
                userTokenHeader(token)
            }
            return response.body()
        }
    }
}
