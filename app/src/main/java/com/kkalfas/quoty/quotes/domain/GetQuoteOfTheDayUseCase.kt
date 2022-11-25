package com.kkalfas.quoty.quotes.domain

import com.kkalfas.quoty.quotes.domain.model.QuoteModel
import javax.inject.Inject

class GetQuoteOfTheDayUseCase @Inject constructor(
    private val repository: QuotesRepository
) {
    suspend operator fun invoke(): QuoteModel {
        val (uuid, author, body) = repository.quoteOfTheDay()
        return QuoteModel(uuid, author, body)
    }
}
