package com.kkalfas.quoty.quotes.data.remote

import com.kkalfas.quoty.quotes.data.model.QuoteEntity
import com.kkalfas.quoty.quotes.data.model.QuotesPaginationModel
import javax.inject.Inject

class QuotesRemoteDataSource @Inject constructor(
    private val quotesService: QuotesService,
) {

    suspend fun getQuoteOfTheDay(): QuoteEntity = quotesService.quoteOfTheDay().quote.toEntity()

    suspend fun getQuotes(page: Int): QuotesPaginationModel {
        val response = quotesService.quotes(page)
        return QuotesPaginationModel(response.page, response.isLastPage, response.quotes.map { it.toEntity() })
    }
}
