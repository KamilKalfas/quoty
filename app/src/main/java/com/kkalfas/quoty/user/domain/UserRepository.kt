package com.kkalfas.quoty.user.domain

import com.kkalfas.quoty.user.data.model.UserEntity

interface UserRepository {

    suspend fun getUser() : UserEntity
}
