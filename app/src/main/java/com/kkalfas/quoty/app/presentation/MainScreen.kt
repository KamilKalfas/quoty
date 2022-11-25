package com.kkalfas.quoty.app.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.kkalfas.quoty.app.presentation.bottomnav.BottomNavItem
import com.kkalfas.quoty.app.presentation.bottomnav.BottomNavigationComponent
import com.kkalfas.quoty.app.presentation.navigation.NavigationGraph
import com.kkalfas.quoty.app.presentation.theme.QuotyTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    val navController = rememberAnimatedNavController()
    QuotyTheme {
        Scaffold(
            backgroundColor = MaterialTheme.colors.surface,
            drawerGesturesEnabled = false,
            bottomBar = {
                BottomNavigationComponent(
                    navHostController = navController,
                    selectedNavigation = BottomNavItem.Home,
                    onNavigationSelected = { selected ->
                        navController.navigate(selected.screen.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        ) {
            NavigationGraph(
                navController = navController,
                paddingValues = it
            )
        }
    }
}
