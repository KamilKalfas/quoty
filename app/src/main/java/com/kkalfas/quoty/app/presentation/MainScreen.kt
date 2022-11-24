package com.kkalfas.quoty.app.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kkalfas.quoty.app.presentation.theme.QuotyTheme

@Composable
fun MainScreen(
    content: @Composable () -> Unit
) {
    QuotyTheme {
        Scaffold(
            drawerGesturesEnabled = false
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .padding(it)
                    .navigationBarsPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = { content() }
            )
        }
    }
}
