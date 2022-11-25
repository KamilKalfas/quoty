package com.kkalfas.quoty.session.domain

import androidx.annotation.WorkerThread

interface SessionStore {
    var userToken: String
    var login: String

    @WorkerThread
    fun clearSessionStore()
}
