package com.kkalfas.quoty.quotes.data.model

data class QuoteEntity(
    val uuid: UInt,
    val author: String,
    val body: String,
    val tags: List<String>,
    val favs : Int,
    val upvotes: Int,
    val downwotes: Int
)
