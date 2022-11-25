package com.kkalfas.quoty.user.domain.model

data class UserModel(
    val login: String,
    val profilePicUrl: String,
    val followers: Int,
    val following: Int,
    val favCount: Int
)
