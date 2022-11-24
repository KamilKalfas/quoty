package com.kkalfas.quoty.quotes

import com.kkalfas.quoty.quotes.data.QuotesRemoteDataSource
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val dataSource: QuotesRemoteDataSource
) {
    operator fun invoke(page: Int) = dataSource.getQuotes(page)
}
