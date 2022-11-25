package com.kkalfas.quoty.quotes

import com.kkalfas.quoty.mocks.MockkTest
import com.kkalfas.quoty.quotes.domain.GetQuotesUseCase
import com.kkalfas.quoty.quotes.domain.QuotesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import org.junit.Test

class GetQuotesUseCaseTest : MockkTest() {

    private val repository: QuotesRepository = mockk()
    private val subject = GetQuotesUseCase(repository)

    @Test
    fun `when invoked then calls repository quotesStream`() {
        // given
        coEvery { repository.quotesStream() } returns mockk()

        // when
        subject()

        coVerify(exactly = 1) {
            repository.quotesStream()
        }
    }
}
