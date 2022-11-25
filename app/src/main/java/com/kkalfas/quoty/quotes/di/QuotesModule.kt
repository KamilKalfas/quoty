package com.kkalfas.quoty.quotes.di

import com.kkalfas.quoty.quotes.data.QuotesRepositoryImpl
import com.kkalfas.quoty.quotes.data.remote.QuotesService
import com.kkalfas.quoty.quotes.domain.QuotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class QuotesModule {

    @Singleton
    @Binds
    abstract fun bindQuoteServiceImpl(impl: QuotesService.Impl): QuotesService

    @Singleton
    @Binds
    abstract fun bindRepository(impl: QuotesRepositoryImpl) : QuotesRepository
}
