package com.kkalfas.quoty.quoteoftheday.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteOfTheDayResponse(
    @SerialName("qotd_date") val timestamp: String,
    @SerialName("quote") val quote: QuoteRemoteModel
)

@Serializable
data class QuoteRemoteModel(
    @SerialName("id") val uuid: UInt,
    @SerialName("author") val author: String,
    @SerialName("body") val body: String
)
