package com.kkalfas.quoty.quoteoftheday

import com.kkalfas.quoty.mocks.MockkTest
import com.kkalfas.quoty.quoteoftheday.model.QuoteOfTheDayResponse
import com.kkalfas.quoty.quoteoftheday.model.QuoteRemoteModel
import com.kkalfas.quoty.quotes.data.QuotesService
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetQuoteOfTheDayUseCaseTest : MockkTest() {

    private val quotesService: QuotesService = mockk()
    private val subject = GetQuoteOfTheDayUseCase(quotesService)

    @Test
    fun `given model from service when invoked then returns mapped model`() {
        // given
        val givenQuote = QuoteRemoteModel(
            uuid = 1u,
            body = "body",
            author = "author"
        )
        val givenResponse = QuoteOfTheDayResponse(
            timestamp = "timestamp",
            quote = givenQuote
        )
        coEvery { quotesService.quoteOfTheDay() } returns givenResponse

        // when
        val result = runBlocking { subject() }

        // then
        coVerify(exactly = 1) {
            quotesService.quoteOfTheDay()
        }
        with(result) {
            assertThat(uuid).isEqualTo(givenQuote.uuid)
            assertThat(author).isEqualTo(givenQuote.author)
            assertThat(body).isEqualTo(givenQuote.body)
        }
    }
}
