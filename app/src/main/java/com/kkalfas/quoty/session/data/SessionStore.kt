package com.kkalfas.quoty.session.data

import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import com.kkalfas.quoty.session.domain.SessionStore


private const val ENCRYPTED_PREFS_FILE = "encrypted_storage.txt"
private const val KEY_USER_TOKEN = "userToken"
private const val KEY_LOGIN = "login"

class PreferencesSessionStore(
    storageProvider: SessionStorageProvider
) : SessionStore {

    private val sessionPreferences: SharedPreferences by lazy {
        storageProvider.provide(ENCRYPTED_PREFS_FILE)
    }

    override var userToken: String
        get() = obtain(KEY_USER_TOKEN)
        set(value) = store(KEY_USER_TOKEN, value)

    override var login: String
        get() = obtain(KEY_LOGIN)
        set(value) = store(KEY_LOGIN, value)

    @WorkerThread
    override fun clearSessionStore() = with(sessionPreferences.edit()) {
        clear()
        apply()
    }

    @WorkerThread
    private fun store(key: String, value: String) {
        with(sessionPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    @WorkerThread
    private fun obtain(key: String): String = sessionPreferences.getString(key, "")!!
}
