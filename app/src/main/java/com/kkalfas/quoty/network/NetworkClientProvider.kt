package com.kkalfas.quoty.network

import com.kkalfas.quoty.BuildConfig
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

/**
 * network layer separation of 3rd party
 */
interface NetworkClientProvider<T> {
    val client: T
}

@OptIn(ExperimentalSerializationApi::class)
class KtorClientProvider(engine: HttpClientEngine) : NetworkClientProvider<HttpClient> {
    override val client: HttpClient by lazy {
        HttpClient(engine) {
            install(UserAgent) { agent = "ktor" }

            // JSON serialization
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        explicitNulls = false
                    }
                )
            }

            // default headers
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

            // logging
            if (BuildConfig.DEBUG) {
                install(Logging) {
                    level = LogLevel.ALL
                }
            }
        }
    }

}
