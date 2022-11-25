package com.kkalfas.quoty.session.data

import com.kkalfas.quoty.session.data.remote.SessionService
import com.kkalfas.quoty.session.domain.SessionRepository
import com.kkalfas.quoty.session.domain.SessionStore
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionService: SessionService,
    private val sessionStore: SessionStore
) : SessionRepository {

    override suspend fun createSession(login: String, password: String) {
        val response = sessionService.createSession(login, password)
        sessionStore.userToken = response.token
        sessionStore.login = response.login
    }

    override suspend fun destroySession() {
        sessionService.destroySession()
        sessionStore.userToken = ""
        sessionStore.login = ""
    }

    override fun isSessionActive() : Boolean {
        return sessionStore.userToken != ""
    }
}
