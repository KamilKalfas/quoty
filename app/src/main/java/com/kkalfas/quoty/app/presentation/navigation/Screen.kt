package com.kkalfas.quoty.app.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Profile : Screen("profile")
}

sealed class HomeDestination(val route: String) {
    object QuoteDetails : HomeDestination("home/quotes/{id}") {
        const val ARGS_ID = "id"

        val arguments = listOf(
            navArgument(ARGS_ID) { type = NavType.IntType }
        )

        fun destination(id: Int): String {
            return "home/quotes/$id"
        }
    }
}
