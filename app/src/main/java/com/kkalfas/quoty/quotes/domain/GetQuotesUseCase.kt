package com.kkalfas.quoty.quotes.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.kkalfas.quoty.quotes.domain.model.QuoteItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository: QuotesRepository
) {
    operator fun invoke(): Flow<PagingData<QuoteItemModel>> {
        return repository.quotesStream()
            .map {
                it.map { entity ->
                    QuoteItemModel(entity.uuid, entity.author, entity.body)
                }
            }
    }
}
