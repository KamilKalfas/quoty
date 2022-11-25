package com.kkalfas.quoty.app.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.kkalfas.quoty.app.presentation.navigation.transitions.defaultEnterTransition
import com.kkalfas.quoty.app.presentation.navigation.transitions.defaultExitTransition
import com.kkalfas.quoty.app.presentation.navigation.transitions.defaultPopEnterTransition
import com.kkalfas.quoty.app.presentation.navigation.transitions.defaultPopExitTransition
import com.kkalfas.quoty.home.presentation.HomeScreen
import com.kkalfas.quoty.quotes.presentation.QuoteDetailsScreen
import com.kkalfas.quoty.user.presentation.ProfileScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    AnimatedNavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = defaultEnterTransition,
        exitTransition = defaultExitTransition,
        popEnterTransition = defaultPopEnterTransition,
        popExitTransition = defaultPopExitTransition
    ) {
        composable(Screen.Home.route) {
            HomeScreen(onItemClick = {
                navController.navigate(HomeDestination.QuoteDetails.destination(it))
            })
        }
        composable(
            route = HomeDestination.QuoteDetails.route,
            arguments = HomeDestination.QuoteDetails.arguments
        ) {
            QuoteDetailsScreen(
                onNavBackAction = { navController.navigateUp() }
            )
        }
        composable(Screen.Search.route) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "No time for ${it.destination.route}")
            }
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}
