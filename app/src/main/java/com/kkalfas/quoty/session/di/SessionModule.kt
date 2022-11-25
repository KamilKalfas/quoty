package com.kkalfas.quoty.session.di

import android.content.Context
import com.kkalfas.quoty.session.data.PreferencesSessionStore
import com.kkalfas.quoty.session.data.SessionRepositoryImpl
import com.kkalfas.quoty.session.data.SessionStorageProvider
import com.kkalfas.quoty.session.data.remote.SessionService
import com.kkalfas.quoty.session.domain.SessionRepository
import com.kkalfas.quoty.session.domain.SessionStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SessionModule {

    @Singleton
    @Binds
    abstract fun bindSessionRepository(impl: SessionRepositoryImpl) : SessionRepository

    @Singleton
    @Binds
    abstract fun bindSessionService(impl: SessionService.Impl) : SessionService

    companion object {
        @Singleton
        @Provides
        fun provideSessionStore(@ApplicationContext context: Context) : SessionStore = PreferencesSessionStore(SessionStorageProvider(context))
    }
}
