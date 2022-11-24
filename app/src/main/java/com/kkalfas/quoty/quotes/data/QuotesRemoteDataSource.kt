package com.kkalfas.quoty.quotes.data

import com.kkalfas.quoty.quotes.data.model.QuoteEntity
import com.kkalfas.quoty.quotes.data.model.QuotesPaginationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuotesRemoteDataSource @Inject constructor(
    private val quotesService: QuotesService
) {

    suspend fun getQuoteOfTheDay(): QuoteEntity = quotesService.quoteOfTheDay().quote.toEntity()

    fun getQuotes(page: Int): Flow<QuotesPaginationModel> = flow {
        val response = quotesService.quotes(page)
        emit(QuotesPaginationModel(response.page, response.isLastPage, response.quotes.map { it.toEntity() }))
    }
}
