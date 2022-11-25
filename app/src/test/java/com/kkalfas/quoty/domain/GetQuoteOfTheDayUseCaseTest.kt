package com.kkalfas.quoty.domain

import com.kkalfas.quoty.mocks.MockkTest
import com.kkalfas.quoty.quotes.data.model.QuoteEntity
import com.kkalfas.quoty.quotes.domain.GetQuoteOfTheDayUseCase
import com.kkalfas.quoty.quotes.domain.QuotesRepository
import com.kkalfas.quoty.quotes.domain.model.QuoteModel
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetQuoteOfTheDayUseCaseTest : MockkTest() {

    private val repository: QuotesRepository = mockk()
    private val subject = GetQuoteOfTheDayUseCase(repository)

    @Test
    fun `when invoked then calls repository quoteOfTheDay and return mapped model`() {
        // given
        val givenQuote = QuoteEntity(
            uuid = 1u,
            body = "body",
            author = "author"
        )
        coEvery { repository.quoteOfTheDay() } returns givenQuote

        // when
        val result = runBlocking { subject() }

        // then
        coVerify(exactly = 1) {
            repository.quoteOfTheDay()
        }
        with(result) {
            assertThat(result).isInstanceOf(QuoteModel::class.java)
            assertThat(uuid).isEqualTo(givenQuote.uuid)
            assertThat(author).isEqualTo(givenQuote.author)
            assertThat(body).isEqualTo(givenQuote.body)
        }
    }
}
