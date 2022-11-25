package com.kkalfas.quoty.quotes.domain

import androidx.paging.PagingData
import com.kkalfas.quoty.quotes.data.model.QuoteEntity
import kotlinx.coroutines.flow.Flow

interface QuotesRepository {

    suspend fun quoteOfTheDay(): QuoteEntity

    fun quotesStream(): Flow<PagingData<QuoteEntity>>
}
