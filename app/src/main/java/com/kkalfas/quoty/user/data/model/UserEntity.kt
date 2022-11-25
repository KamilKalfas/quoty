package com.kkalfas.quoty.user.data.model

data class UserEntity(
    val login: String,
    val profilePicUrl: String,
    val followers: Int,
    val following: Int,
    val favCount: Int
)
