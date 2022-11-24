package com.kkalfas.quoty.quotes

import com.kkalfas.quoty.mocks.MockkTest
import com.kkalfas.quoty.quotes.data.QuotesRemoteDataSource
import com.kkalfas.quoty.quotes.data.model.QuoteEntity
import com.kkalfas.quoty.quotes.data.model.QuotesPaginationModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.slot
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetQuotesUseCaseTest : MockkTest() {

    private val dataSource: QuotesRemoteDataSource = mockk()
    private val subject = GetQuotesUseCase(dataSource)

    @Test
    fun `given param when invoked then calls data source getQuotes with param`() {
        // given
        val pageSlot = slot<Int>()
        val givenPageNumber = 1
        val givenQuoteEntities = listOf(QuoteEntity(1u, "a", "b"))
        val givenQuotes = QuotesPaginationModel(
            page = givenPageNumber,
            isLastPage = false,
            quotes = givenQuoteEntities
        )
        coEvery { dataSource.getQuotes(givenPageNumber) } returns flowOf(givenQuotes)

        // when
        val flow = subject.invoke(givenPageNumber)

        // then
        runBlocking {
            flow.collect {
                assertThat(it.page).isEqualTo(givenQuotes.page)
                assertThat(it.isLastPage).isEqualTo(givenQuotes.isLastPage)
                assertThat(it.quotes.size).isEqualTo(givenQuoteEntities.size)
                assertThat(it.quotes[0]).isEqualTo(givenQuoteEntities[0])
            }
        }

        // then
        coVerify(exactly = 1) {
            dataSource.getQuotes(capture(pageSlot))
        }
        assertThat(pageSlot.captured).isEqualTo(givenPageNumber)
    }
}
