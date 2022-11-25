package com.kkalfas.quoty.session.domain

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(login: String, password: String) {
        sessionRepository.createSession(login, password)
    }
}
