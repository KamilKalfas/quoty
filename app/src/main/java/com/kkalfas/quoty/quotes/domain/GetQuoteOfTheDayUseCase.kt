package com.kkalfas.quoty.quotes.domain

import com.kkalfas.quoty.quotes.domain.model.QuoteItemModel
import javax.inject.Inject

class GetQuoteOfTheDayUseCase @Inject constructor(
    private val repository: QuotesRepository
) {
    suspend operator fun invoke(): QuoteItemModel {
        val (uuid, author, body) = repository.quoteOfTheDay()
        return QuoteItemModel(uuid, author, body)
    }
}
