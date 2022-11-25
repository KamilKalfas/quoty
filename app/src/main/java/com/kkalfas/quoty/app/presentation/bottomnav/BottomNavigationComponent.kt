package com.kkalfas.quoty.app.presentation.bottomnav

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationComponent(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = MaterialTheme.colors.secondaryVariant,
    selectedNavigation: BottomNavItem,
    onNavigationSelected: (BottomNavItem) -> Unit
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Profile
    )

    BottomNavigation(
        modifier = modifier.navigationBarsPadding(),
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = 0.dp
    ) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(imageVector = item.icon, contentDescription = stringResource(item.titleId))
                },
                alwaysShowLabel = false,
                selected = currentDestination?.hierarchy?.any { selectedNavigation == item } == true,
                onClick = { onNavigationSelected(item) }
            )
        }
    }
}
