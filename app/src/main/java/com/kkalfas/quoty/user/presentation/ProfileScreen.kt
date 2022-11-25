package com.kkalfas.quoty.user.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kkalfas.quoty.app.presentation.theme.QuotyTheme
import com.kkalfas.quoty.home.presentation.ErrorToast
import com.kkalfas.quoty.user.domain.model.UserModel
import java.util.*

@Preview(showSystemUi = true)
@Composable
private fun PreviewProfileScreenNotLoggedIn() {
    QuotyTheme {
        YouNeedToLogin(
            onLoginButtonAction = { _, _ -> }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewProfileScreenLoggedIn() {
    QuotyTheme {
        UserLoggedIn(
            onLogoutButtonAction = {},
            profile = UserModel(
                login = "mascarpone",
                profilePicUrl = "https://pbs.twimg.com/profile_images/2160924471/Screen_Shot_2012-04-23_at_9.23.44_PM_.png",
                followers = 101,
                following = 0,
                favCount = 7
            )
        )
    }
}

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    ErrorToast(state.errorMessage)
    ProfileContent(
        state = state,
        onLoginButtonAction = viewModel::onLoginAction,
        onLogoutButtonAction = viewModel::onLogoutAction
    )
}


@Composable
private fun ProfileContent(
    state: ProfileUiState,
    onLoginButtonAction: (String, String) -> Unit,
    onLogoutButtonAction: () -> Unit
) {
    if (state.isSessionActive) UserLoggedIn(
        onLogoutButtonAction = onLogoutButtonAction,
        profile = state.profile
    )
    else YouNeedToLogin(
        onLoginButtonAction = onLoginButtonAction
    )
}

@Composable
private fun UserLoggedIn(
    onLogoutButtonAction: () -> Unit,
    profile: UserModel?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = onLogoutButtonAction
            ) {
                Text(
                    text = "Logout".uppercase(Locale.getDefault()),
                    color = MaterialTheme.colors.secondaryVariant
                )
            }
        }
        profile?.let {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(it.profilePicUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)
            )
            Text(
                text = "LOGIN: ${it.login}",
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = "FOLLOWERS: ${it.followers}",
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = "FOLLOWING: ${it.following}",
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = "FAVOURITES: ${it.favCount}",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
private fun YouNeedToLogin(
    onLoginButtonAction: (String, String) -> Unit
) {
    var login by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "You need to log in first!",
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = login,
            onValueChange = { v -> login = v }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { v -> password = v },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier
                .width(TextFieldDefaults.MinWidth)
                .padding(horizontal = 16.dp),
            onClick = { onLoginButtonAction(login.text, password.text) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondaryVariant,
                contentColor = MaterialTheme.colors.onSecondary
            )
        ) {
            Text(text = "Login")
        }
    }
}
