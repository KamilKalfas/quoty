package com.kkalfas.quoty.quotes.domain.model

data class QuoteModel(
    val uuid: UInt,
    val author: String,
    val body: String
)
