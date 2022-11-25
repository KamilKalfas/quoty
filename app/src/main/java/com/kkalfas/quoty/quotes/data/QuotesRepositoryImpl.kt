package com.kkalfas.quoty.quotes.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kkalfas.quoty.quotes.data.model.QuoteEntity
import com.kkalfas.quoty.quotes.data.remote.QuotesRemoteDataSource
import com.kkalfas.quoty.quotes.domain.QuotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val FAVQS_PAGE_SIZE = 25

class QuotesRepositoryImpl @Inject constructor(
    private val quotesRemoteDataSource: QuotesRemoteDataSource,
    private val quotesPagingSource: QuotesPagingSource
) : QuotesRepository {

    override suspend fun quoteOfTheDay() = quotesRemoteDataSource.getQuoteOfTheDay()

    override suspend fun quote(id: UInt) = quotesRemoteDataSource.getQuoteById(id)

    override fun quotesStream(): Flow<PagingData<QuoteEntity>> {
        return Pager(
            pagingSourceFactory = { quotesPagingSource },
            config = PagingConfig(pageSize = FAVQS_PAGE_SIZE)
        ).flow
    }
}
