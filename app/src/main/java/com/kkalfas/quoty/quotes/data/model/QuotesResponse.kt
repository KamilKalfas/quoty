package com.kkalfas.quoty.quotes.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuotesResponse(
    @SerialName("page") val page: Int,
    @SerialName("last_page") val isLastPage: Boolean,
    @SerialName("quotes") val quotes: List<QuoteRemoteModel>
)
