package com.kkalfas.quoty.quoteoftheday.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kkalfas.quoty.app.presentation.theme.QuotyTheme
import com.kkalfas.quoty.quoteoftheday.model.QuoteOfTheDayUiModel

@Preview
@Composable
private fun PreviewQOTDComponent() {
    QuotyTheme {
        QuoteOfTheDay(quote = QuoteOfTheDayUiModel("Abe", "Ex nihilo nihil fit Ex nihilo nihil fit Ex nihilo nihil fit Ex nihilo nihil fit"))
    }
}

@Composable
fun QuoteOfTheDay(
    modifier: Modifier = Modifier,
    quote: QuoteOfTheDayUiModel
) {
    Column(
        modifier = modifier.then(
            Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colors.background)
                .padding(all = 24.dp)
                .fillMaxWidth()
        ),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = quote.body,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.align(Alignment.End),
            text = quote.author,
            style = MaterialTheme.typography.subtitle1
        )
    }
}
