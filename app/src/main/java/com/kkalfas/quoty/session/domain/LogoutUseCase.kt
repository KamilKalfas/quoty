package com.kkalfas.quoty.session.domain

import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
){
    suspend operator fun invoke() = sessionRepository.destroySession()
}
