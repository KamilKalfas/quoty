package com.kkalfas.quoty.di

import com.kkalfas.quoty.network.NetworkClientProvider
import com.kkalfas.quoty.network.ktor.KtorClientProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideClientProvider(): NetworkClientProvider<HttpClient> = KtorClientProvider(Android.create())

    @Singleton
    @Provides
    fun provideClient(clientProvider: NetworkClientProvider<HttpClient>): HttpClient = clientProvider.client
}
