package com.kkalfas.quoty.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.kkalfas.quoty.R
import com.kkalfas.quoty.quotes.domain.model.QuoteItemModel
import com.kkalfas.quoty.quotes.presentation.QuoteOfTheDay

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val quotes = viewModel.pagingState.collectAsLazyPagingItems()
    ErrorToast(state.errorMessage)
    HomeContainer(
        modifier = modifier,
        state = state,
        quotes = quotes,
        onItemClick = onItemClick
    )
}

@Composable
private fun HomeContainer(
    modifier: Modifier = Modifier,
    state: HomeUiState,
    quotes: LazyPagingItems<QuoteItemModel>,
    onItemClick: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        state.quoteOfTheDay?.let {
            QuoteOfTheDaySection(quote = it)
        }
        QuotesSection(
            quotes = quotes,
            onItemClick = onItemClick
        )
    }
}

@Composable
private fun QuoteOfTheDaySection(
    quote: QuoteItemModel
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

@Composable
private fun QuotesSection(
    quotes: LazyPagingItems<QuoteItemModel>,
    onItemClick: (Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Text(
                text = "Quotes",
                style = MaterialTheme.typography.h5
            )
        }
        paginationLoadingState(quotes.loadState.prepend)
        paginationLoadingState(quotes.loadState.refresh)
        paginationLoadingState(quotes.loadState.append)
        items(
            items = quotes,
            key = { it.uuid.toInt() }
        ) {
            it?.let {
                QuoteRowItem(
                    quote = it,
                    onItemClick = onItemClick
                )
            }
        }

    }
}

private fun LazyListScope.paginationLoadingState(loadState: LoadState) {
    when (loadState) {
        is LoadState.NotLoading -> Unit
        is LoadState.Loading -> {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp), color = MaterialTheme.colors.secondary)
                }
            }
        }
        is LoadState.Error -> {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = loadState.error.message ?: "",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.error
                    )
                }
            }
        }
    }
}

@Composable
private fun QuoteRowItem(
    quote: QuoteItemModel,
    onItemClick: (Int) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colors.background)
            .padding(all = 24.dp)
            .clickable { onItemClick(quote.uuid.toInt()) }
    ) {
        Text(text = quote.body)
        Divider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colors.primaryVariant,
            thickness = 1.dp
        )
        Text(text = quote.author)
    }
}
