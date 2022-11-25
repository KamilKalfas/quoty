package com.kkalfas.quoty.user.domain

import com.kkalfas.quoty.user.domain.model.UserModel
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke() : UserModel {
        val (login, profilePicUrl, followers, following, favCount) = repository.getUser()
        return UserModel(login, profilePicUrl, followers, following, favCount)
    }
}
