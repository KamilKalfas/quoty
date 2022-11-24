package com.kkalfas.quoty.quoteoftheday

import com.kkalfas.quoty.quotes.data.QuotesService
import com.kkalfas.quoty.quotes.data.model.QuoteEntity
import javax.inject.Inject

class GetQuoteOfTheDayUseCase @Inject constructor(
    private val quotesService: QuotesService
) {
    suspend operator fun invoke() : QuoteEntity {
        val response = quotesService.quoteOfTheDay().quote
        return QuoteEntity(
            uuid = response.uuid,
            author = response.author,
            body = response.body
        )
    }
}
