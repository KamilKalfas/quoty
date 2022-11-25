package com.kkalfas.quoty.home.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ErrorToast(
    errorMessage: String
) {
    if (errorMessage != "") Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_SHORT).show()
}
