package com.kkalfas.quoty.quoteoftheday

import com.kkalfas.quoty.quotes.data.QuotesRemoteDataSource
import javax.inject.Inject

class GetQuoteOfTheDayUseCase @Inject constructor(
    private val dataSource: QuotesRemoteDataSource
) {
    suspend operator fun invoke() = dataSource.getQuoteOfTheDay()
}
