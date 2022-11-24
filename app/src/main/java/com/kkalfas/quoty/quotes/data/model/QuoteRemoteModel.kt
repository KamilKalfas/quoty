package com.kkalfas.quoty.quotes.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteRemoteModel(
    @SerialName("id") val uuid: UInt,
    @SerialName("author") val author: String,
    @SerialName("body") val body: String
) {
    fun toEntity() = QuoteEntity(
        uuid = uuid,
        author = author,
        body = body
    )
}
