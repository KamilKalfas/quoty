package com.kkalfas.quoty.quoteoftheday

import com.kkalfas.quoty.mocks.MockkTest
import com.kkalfas.quoty.quoteoftheday.model.QuoteOfTheDayResponse
import com.kkalfas.quoty.quotes.data.QuotesRemoteDataSource
import com.kkalfas.quoty.quotes.data.QuotesService
import com.kkalfas.quoty.quotes.data.model.QuoteEntity
import com.kkalfas.quoty.quotes.data.model.QuoteRemoteModel
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetQuoteOfTheDayUseCaseTest : MockkTest() {

    private val dataSource: QuotesRemoteDataSource = mockk()
    private val subject = GetQuoteOfTheDayUseCase(dataSource)

    @Test
    fun `when invoked then calls data source getQuoteOfTheDay`() {
        // given
        val givenQuote = QuoteEntity(
            uuid = 1u,
            body = "body",
            author = "author"
        )
        coEvery { dataSource.getQuoteOfTheDay() } returns givenQuote

        // when
        val result = runBlocking { subject() }

        // then
        coVerify(exactly = 1) {
            dataSource.getQuoteOfTheDay()
        }
        with(result) {
            assertThat(uuid).isEqualTo(givenQuote.uuid)
            assertThat(author).isEqualTo(givenQuote.author)
            assertThat(body).isEqualTo(givenQuote.body)
        }
    }
}
