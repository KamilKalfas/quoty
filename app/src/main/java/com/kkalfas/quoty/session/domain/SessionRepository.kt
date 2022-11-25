package com.kkalfas.quoty.session.domain

interface SessionRepository {
    suspend fun createSession(login: String, password: String)
    suspend fun destroySession()
    fun isSessionActive(): Boolean
}
