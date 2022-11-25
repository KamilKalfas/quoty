package com.kkalfas.quoty.user.di

import com.kkalfas.quoty.user.data.UserRepositoryImpl
import com.kkalfas.quoty.user.data.remote.UserService
import com.kkalfas.quoty.user.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserModule {

    @Singleton
    @Binds
    abstract fun bindUserRepository(impl: UserRepositoryImpl) : UserRepository

    @Singleton
    @Binds
    abstract fun bindSessionService(impl: UserService.Impl) : UserService
}
