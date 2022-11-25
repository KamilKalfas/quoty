package com.kkalfas.quoty.session.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DestroySessionResponse(
    @SerialName("message") val message: String
)
