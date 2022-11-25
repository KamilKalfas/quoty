package com.kkalfas.quoty.user.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUserResponse(
    @SerialName("login") val login: String,
    @SerialName("pic_url") val pictureUrl: String,
    @SerialName("followers") val followers: Int,
    @SerialName("following") val following: Int,
    @SerialName("public_favorites_count") val favCount: Int
)
