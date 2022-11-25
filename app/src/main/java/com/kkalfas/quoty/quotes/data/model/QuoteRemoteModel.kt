package com.kkalfas.quoty.quotes.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteRemoteModel(
    @SerialName("id") val uuid: UInt,
    @SerialName("author") val author: String,
    @SerialName("body") val body: String,
    @SerialName("tags") val tags: List<String>,
    @SerialName("favorites_count") val favs : Int,
    @SerialName("upvotes_count") val upvotes: Int,
    @SerialName("downvotes_count") val downwotes: Int
) {
    fun toEntity() = QuoteEntity(
        uuid = uuid,
        author = author,
        body = body,
        tags = tags,
        favs = favs,
        upvotes = upvotes,
        downwotes = downwotes
    )
}
