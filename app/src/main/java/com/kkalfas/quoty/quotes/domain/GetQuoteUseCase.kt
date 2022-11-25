package com.kkalfas.quoty.quotes.domain

import com.kkalfas.quoty.quotes.domain.model.QuoteDetailsModel
import javax.inject.Inject

class GetQuoteUseCase @Inject constructor(
    private val repository: QuotesRepository
) {
    suspend operator fun invoke(id: UInt): QuoteDetailsModel {
        val entity = repository.quote(id)
        return QuoteDetailsModel(
            uuid = entity.uuid,
            author = entity.author,
            body = entity.body,
            tags = entity.tags,
            favs = entity.favs,
            upvotes = entity.upvotes,
            downwotes = entity.downwotes
        )
    }
}
