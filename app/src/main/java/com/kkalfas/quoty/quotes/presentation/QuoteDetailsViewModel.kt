package com.kkalfas.quoty.quotes.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkalfas.quoty.app.AppDispatchers
import com.kkalfas.quoty.app.Async
import com.kkalfas.quoty.app.Async.Companion.then
import com.kkalfas.quoty.app.presentation.navigation.HomeDestination
import com.kkalfas.quoty.quotes.domain.GetQuoteUseCase
import com.kkalfas.quoty.quotes.domain.model.QuoteDetailsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val STATE_KEY_ERROR_MESSAGE = "error_message"
private const val STATE_KEY_LOADING = "loading"

@HiltViewModel
class QuoteDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dispatchers: AppDispatchers,
    private val getQuoteUseCase: GetQuoteUseCase
) : ViewModel() {

    private val _isLoading = savedStateHandle.getStateFlow(STATE_KEY_LOADING, true)
    private val _errorMessage = savedStateHandle.getStateFlow(STATE_KEY_ERROR_MESSAGE, "")
    private val _quote: MutableStateFlow<QuoteDetailsModel?> = MutableStateFlow(null)
    private val _quoteId = requireNotNull(savedStateHandle.get<Int>(HomeDestination.QuoteDetails.ARGS_ID)).toUInt()

    val state: StateFlow<QuoteDetailsUiState> = combine(
        _isLoading, _errorMessage, _quote
    ) { isLoading, errorMessage, quote ->
        QuoteDetailsUiState(
            isLoading = isLoading,
            errorMessage = errorMessage,
            quote = quote
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = QuoteDetailsUiState()
    )

    init {
        fetchQuoteDetails(_quoteId)
    }

    private fun fetchQuoteDetails(id: UInt) {
        viewModelScope.launch(dispatchers.io) {
            Async.execute {
                getQuoteUseCase(id)
            }.then(
                onSuccess = {
                    _quote.value = it
                    savedStateHandle[STATE_KEY_LOADING] = false
                },
                onError = {
                    savedStateHandle[STATE_KEY_ERROR_MESSAGE] = it::class.simpleName
                    clearError()
                }
            )
        }
    }

    private suspend fun clearError() {
        delay(1000)
        savedStateHandle[STATE_KEY_ERROR_MESSAGE] = ""
    }
}

data class QuoteDetailsUiState(
    val isLoading: Boolean = true,
    val quote: QuoteDetailsModel? = null,
    val errorMessage: String = ""
)
