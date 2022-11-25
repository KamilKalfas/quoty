package com.kkalfas.quoty.session.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionResponse(
    @SerialName("User-Token") val token: String,
    @SerialName("login") val login: String,
    @SerialName("email") val email: String,
)
