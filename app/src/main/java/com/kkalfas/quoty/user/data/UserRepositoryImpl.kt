package com.kkalfas.quoty.user.data

import com.kkalfas.quoty.session.domain.SessionStore
import com.kkalfas.quoty.user.data.model.UserEntity
import com.kkalfas.quoty.user.data.remote.UserService
import com.kkalfas.quoty.user.domain.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val service: UserService,
    private val sessionStore: SessionStore
) : UserRepository {

    override suspend fun getUser(): UserEntity {
        val (login, profilePicUrl, followers, following, favCount) = service.getUser(sessionStore.userToken, sessionStore.login)
        return UserEntity(login, profilePicUrl, followers, following, favCount)
    }
}
