package com.kkalfas.quoty.network.ktor

import android.util.Log
import com.kkalfas.quoty.BuildConfig
import com.kkalfas.quoty.network.NetworkClientProvider
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


class KtorClientProvider(engine: HttpClientEngine) : NetworkClientProvider<HttpClient> {
    override val client: HttpClient by lazy {
        HttpClient(engine) {
            install(UserAgent) { agent = "ktor" }

            installDefaultHeaders()
            installJSONSerialization()
            // logging
            if (BuildConfig.DEBUG) {
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Log.d("BONJOUR", message)
                        }
                    }
                    level = LogLevel.BODY
                }
            }
        }
    }
}

/**
 * Extracted to have same implementation in MockEngine
 */
@OptIn(ExperimentalSerializationApi::class)
fun HttpClientConfig<*>.installJSONSerialization() {
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
}

/**
 * Extracted to have same implementation in MockEngine
 */
fun HttpClientConfig<*>.installDefaultHeaders() {
    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}
