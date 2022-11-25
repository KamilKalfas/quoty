package com.kkalfas.quoty.quotes.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kkalfas.quoty.quotes.data.model.QuoteEntity
import com.kkalfas.quoty.quotes.data.remote.QuotesRemoteDataSource
import javax.inject.Inject

private const val STARTING_INDEX = 1

class QuotesPagingSource @Inject constructor(
    private val dataSource: QuotesRemoteDataSource
) : PagingSource<Int, QuoteEntity>() {

    override fun getRefreshKey(state: PagingState<Int, QuoteEntity>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuoteEntity> {
        val page = params.key ?: STARTING_INDEX
        return try {
            val data = dataSource.getQuotes(page)
            LoadResult.Page(
                data = data.quotes,
                prevKey = if (page == STARTING_INDEX) null else page,
                nextKey = if (data.quotes.isEmpty()) null else page + 1
            )
        } catch (ex: Exception) {
            // maybe TODO probably handle proper exception
            LoadResult.Error(ex)
        }
    }
}
