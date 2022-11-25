package com.kkalfas.quoty.app.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Profile : Screen("profile")
}
