package com.kkalfas.quoty.home.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kkalfas.quoty.app.AppDispatchers
import com.kkalfas.quoty.quotes.domain.GetQuoteOfTheDayUseCase
import com.kkalfas.quoty.quotes.domain.GetQuotesUseCase
import com.kkalfas.quoty.quotes.domain.model.QuoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = true,
    val quoteOfTheDay: QuoteModel? = null,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dispatchers: AppDispatchers,
    private val getQuoteOfTheDayUseCase: GetQuoteOfTheDayUseCase,
    private val getQuotesUseCase: GetQuotesUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _quoteOfTheDay: MutableStateFlow<QuoteModel?> = MutableStateFlow(null)

    val pagingState : Flow<PagingData<QuoteModel>> = getQuotesUseCase().cachedIn(viewModelScope)

    init {
        viewModelScope.launch(dispatchers.io) {
            _quoteOfTheDay.value = getQuoteOfTheDayUseCase()
        }
    }

    val state: StateFlow<HomeUiState> = combine(
        _isLoading, _quoteOfTheDay,
    ) { isLoading, quoteOfTheDay ->
        HomeUiState(
            isLoading = isLoading,
            quoteOfTheDay = quoteOfTheDay
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState()
    )
}
