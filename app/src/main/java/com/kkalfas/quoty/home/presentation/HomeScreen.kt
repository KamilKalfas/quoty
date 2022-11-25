package com.kkalfas.quoty.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kkalfas.quoty.R
import com.kkalfas.quoty.quotes.domain.model.QuoteModel
import com.kkalfas.quoty.quotes.presentation.QuoteOfTheDay

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    HomeContainer(
        modifier = modifier,
        state = state
    )
}

@Composable
private fun HomeContainer(
    modifier: Modifier = Modifier,
    state: HomeUiState
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        state.quoteOfTheDay?.let {
            QuoteOfTheDaySection(quote = it)
        }
    }
}

@Composable
private fun QuoteOfTheDaySection(
    quote: QuoteModel
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = stringResource(id = R.string.home_quote_of_the_day_title),
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Start,
        )
        QuoteOfTheDay(
            quote = quote
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
