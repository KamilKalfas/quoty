package com.kkalfas.quoty.session.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionRequest constructor(
    @SerialName("user") val user: User
)

@Serializable
data class User(
    @SerialName(value = "login") val username: String,
    @SerialName(value = "password") val password: String
)
