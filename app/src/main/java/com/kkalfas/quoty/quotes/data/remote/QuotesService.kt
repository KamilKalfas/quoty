package com.kkalfas.quoty.quotes.data.remote

import com.kkalfas.quoty.BuildConfig
import com.kkalfas.quoty.network.NetworkClientProvider
import com.kkalfas.quoty.network.ktor.authHeader
import com.kkalfas.quoty.quotes.data.model.QuotesResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

private const val HOST = "favqs.com"
private const val PATH_QUOTD = "/api/qotd"
private const val PATH_QUOTES = "/api/quotes"

interface QuotesService {

    suspend fun quoteOfTheDay(): QuoteOfTheDayResponse
    suspend fun quotes(page: Int) : QuotesResponse

    class Impl @Inject constructor(
        clientProvider: NetworkClientProvider<HttpClient>
    ) : QuotesService {

        private val client by lazy { clientProvider.client }

        override suspend fun quoteOfTheDay(): QuoteOfTheDayResponse {
            val response = client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = HOST
                    path(PATH_QUOTD)
                }
            }
            return response.body()
        }

        override suspend fun quotes(page: Int): QuotesResponse {
            val response = client.get {
                authHeader(BuildConfig.API_KEY)
                url {
                    protocol = URLProtocol.HTTPS
                    host = HOST
                    path(PATH_QUOTES)
                }
            }
            return response.body()
        }
    }
}
