package com.kkalfas.quoty.quotes.data.remote

import com.kkalfas.quoty.mocks.InternalServerError
import com.kkalfas.quoty.mocks.MockkTest
import com.kkalfas.quoty.mocks.mockClientRespondWith
import com.kkalfas.quoty.mocks.response.ResponseQuoteOfTheDay
import com.kkalfas.quoty.network.NetworkClientProvider
import io.ktor.client.*
import io.mockk.every
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class QuotesServiceTest : MockkTest() {

    private val clientProvider: NetworkClientProvider<HttpClient> = mockk()
    private val subject = QuotesService.Impl(clientProvider)

    @Test
    fun `given successful response when requesting quote of the day then receiving expected model`() {
        // given
        every { clientProvider.client } returns mockClientRespondWith(
            json = ResponseQuoteOfTheDay()
        )

        // when
        val result = runBlocking { subject.quoteOfTheDay() }

        // then
        assertThat(result).isInstanceOf(QuoteOfTheDayResponse::class.java)
    }

    @Test(expected = InternalServerError::class)
    fun `given error response when requesting quote of the day then receiving exception`() {
        // given
        every { clientProvider.client } returns mockClientRespondWith(
            json = "",
            isSuccess = false
        )

        // when
        val result = runBlocking { subject.quoteOfTheDay() }

        // then
        assertThat(result).isInstanceOf(QuoteOfTheDayResponse::class.java)
    }
}
