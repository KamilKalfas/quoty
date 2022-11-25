package com.kkalfas.quoty.quotes.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkalfas.quoty.app.presentation.theme.QuotyTheme
import com.kkalfas.quoty.home.presentation.ErrorToast
import com.kkalfas.quoty.quotes.domain.model.QuoteDetailsModel

@Preview(showSystemUi = true)
@Composable
private fun PreviewQuoteDetailsScreen() {
    QuotyTheme {
        QuoteDetailsContainer(
            state = QuoteDetailsUiState(
                isLoading = false,
                errorMessage = "",
                quote = QuoteDetailsModel(
                    uuid = 1u,
                    author = "Todd Chavez",
                    body = "Fool me once. Fool me twice. Fool me chicken soup with rice.",
                    tags = listOf("todd", "bojack"),
                    favs = 111,
                    upvotes = 777,
                    downwotes = 1
                )
            )
        )
    }
}

@Composable
fun QuoteDetailsScreen(
    onNavBackAction: () -> Unit,
    viewModel: QuoteDetailsViewModel = hiltViewModel()
) {
    BackHandler {
        onNavBackAction()
    }
    val state by viewModel.state.collectAsState()
    ErrorToast(state.errorMessage)
    QuoteDetailsContainer(
        state = state
    )
}

@Composable
fun QuoteDetailsContainer(
    state: QuoteDetailsUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
                    .alpha(.2f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp), color = MaterialTheme.colors.secondaryVariant)
            }
        }
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colors.background)
                .padding(horizontal = 16.dp)
                .wrapContentSize()
                .defaultMinSize(minHeight = 340.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            state.quote?.let {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Quote by \n ${it.author}",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center
                )
                Div()
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = it.body,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )
                Div()
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "tags: ${it.tags.joinToString(", ")}",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.Center
                )
                Div()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "- ${it.downwotes}",
                        style = MaterialTheme.typography.h3,
                        color = Color.LightGray
                    )
                    Text(
                        text = "+ ${it.upvotes}",
                        style = MaterialTheme.typography.h3,
                        color = Color.Cyan
                    )
                    Text(
                        text = "<3 ${it.favs}",
                        style = MaterialTheme.typography.h3,
                        color = Color.Magenta
                    )
                }
            }
        }
    }
}

@Composable
private fun Div() {
    Divider(
        modifier = Modifier
            .padding(horizontal = 64.dp, vertical = 8.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colors.primaryVariant,
        thickness = 1.dp
    )
}
