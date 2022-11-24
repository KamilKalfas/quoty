package com.kkalfas.quoty.app.di

import com.kkalfas.quoty.network.ktor.KtorClientProvider
import com.kkalfas.quoty.network.NetworkClientProvider
import com.kkalfas.quoty.quotes.data.QuotesService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Singleton
    @Binds
    abstract fun bindQuoteServiceImpl(impl: QuotesService.Impl) : QuotesService

    companion object {
        @Singleton
        @Provides
        fun provideClientProvider(): NetworkClientProvider<HttpClient> = KtorClientProvider(Android.create())

        @Singleton
        @Provides
        fun provideClient(clientProvider: NetworkClientProvider<HttpClient>): HttpClient = clientProvider.client
    }
}
