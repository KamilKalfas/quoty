package com.kkalfas.quoty.quotes.data.remote

import com.kkalfas.quoty.quotes.data.model.QuoteRemoteModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteOfTheDayResponse(
    @SerialName("qotd_date") val timestamp: String,
    @SerialName("quote") val quote: QuoteRemoteModel
)
