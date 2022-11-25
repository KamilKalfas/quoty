package com.kkalfas.quoty.session.data

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SessionStorageProvider(
    private val applicationContext: Context
) {
    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val mainKeyAlias by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { MasterKeys.getOrCreate(keyGenParameterSpec) }

    fun provide(fileName: String): SharedPreferences {
        return EncryptedSharedPreferences.create(
            fileName,
            mainKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}
