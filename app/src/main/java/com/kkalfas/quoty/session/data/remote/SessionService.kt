package com.kkalfas.quoty.session.data.remote

import com.kkalfas.quoty.BuildConfig
import com.kkalfas.quoty.network.NetworkClientProvider
import com.kkalfas.quoty.network.ktor.authHeader
import com.kkalfas.quoty.session.data.model.CreateSessionRequest
import com.kkalfas.quoty.session.data.model.CreateSessionRequestFactory
import com.kkalfas.quoty.session.data.model.CreateSessionResponse
import com.kkalfas.quoty.session.data.model.DestroySessionResponse
import com.kkalfas.quoty.session.data.model.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

private const val HOST = "favqs.com"
private const val PATH_SESSION = "/api/session"

interface SessionService {
    suspend fun createSession(login: String, password: String): CreateSessionResponse
    suspend fun destroySession(): DestroySessionResponse

    class Impl @Inject constructor(
        clientProvider: NetworkClientProvider<HttpClient>
    ) : SessionService {

        private val client by lazy { clientProvider.client }

        override suspend fun createSession(login: String, password: String): CreateSessionResponse {
            val response = client.post {
                url {
                    protocol = URLProtocol.HTTPS
                    host = HOST
                    path(PATH_SESSION)
                }
                authHeader(BuildConfig.API_KEY)
                contentType(ContentType.Application.Json)
                setBody(CreateSessionRequest(User(login, password)))
            }
            return response.body()
        }

        override suspend fun destroySession(): DestroySessionResponse {
            val response = client.delete {
                url {
                    protocol = URLProtocol.HTTPS
                    host = HOST
                    path(PATH_SESSION)
                }
                authHeader(BuildConfig.API_KEY)
            }
            return response.body()
        }
    }
}
