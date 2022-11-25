package com.kkalfas.quoty.session.domain

import javax.inject.Inject

class IsSessionActiveUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    operator fun invoke() = sessionRepository.isSessionActive()
}
