package com.kkalfas.quoty.quotes.data.model

class QuotesPaginationModel(
    val page: Int,
    val isLastPage: Boolean,
    val quotes: List<QuoteEntity>
)
