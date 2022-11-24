package com.kkalfas.quoty.quotes.data.model

import com.kkalfas.quoty.quoteoftheday.model.QuoteOfTheDayUiModel

data class QuoteEntity(
    val uuid: UInt,
    val author: String,
    val body: String
) {
    fun toQuoteOfTheDayModel() = QuoteOfTheDayUiModel(author, body)
}
