package com.kkalfas.quoty.quotes.data

import com.kkalfas.quoty.network.NetworkClientProvider
import com.kkalfas.quoty.quoteoftheday.model.QuoteOfTheDayResponse
import com.kkalfas.quoty.quotes.data.model.QuoteRemoteModel
import com.kkalfas.quoty.quotes.data.model.QuotesResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
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
