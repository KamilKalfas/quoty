package com.kkalfas.quoty.home.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kkalfas.quoty.app.AppDispatchers
import com.kkalfas.quoty.app.Async
import com.kkalfas.quoty.app.Async.Companion.then
import com.kkalfas.quoty.quotes.domain.GetQuoteOfTheDayUseCase
import com.kkalfas.quoty.quotes.domain.GetQuotesUseCase
import com.kkalfas.quoty.quotes.domain.model.QuoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val errorMessage: String = "",
    val quoteOfTheDay: QuoteModel? = null,
)

private const val STATE_KEY_ERROR_MESSAGE = "error_message"

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dispatchers: AppDispatchers,
    private val getQuoteOfTheDayUseCase: GetQuoteOfTheDayUseCase,
    private val getQuotesUseCase: GetQuotesUseCase
) : ViewModel() {

    private val _errorMessage = savedStateHandle.getStateFlow(STATE_KEY_ERROR_MESSAGE, "")
    private val _quoteOfTheDay: MutableStateFlow<QuoteModel?> = MutableStateFlow(null)

    val pagingState: Flow<PagingData<QuoteModel>> = getQuotesUseCase()
        .cachedIn(viewModelScope)
        .retry(3)
        .catch { e ->
            Log.e("HomeViewModel", e.toString())
            savedStateHandle[STATE_KEY_ERROR_MESSAGE] = "Oops something went wrong!"
            clearError()
        }

    init {
        fetchQuoteOfTheDay()
    }

    val state: StateFlow<HomeUiState> = combine(
        _errorMessage, _quoteOfTheDay,
    ) { errorMessage, quoteOfTheDay ->
        HomeUiState(
            errorMessage = errorMessage,
            quoteOfTheDay = quoteOfTheDay
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState()
    )

    private fun fetchQuoteOfTheDay() {
        viewModelScope.launch(dispatchers.io) {
            Async.execute { getQuoteOfTheDayUseCase() }
                .then(
                    onError = {
                        savedStateHandle[STATE_KEY_ERROR_MESSAGE] = it::class.simpleName
                        clearError()
                    },
                    onSuccess = { _quoteOfTheDay.value = it }
                )
        }
    }

    private suspend fun clearError() {
        delay(1000)
        savedStateHandle[STATE_KEY_ERROR_MESSAGE] = ""
    }
}
