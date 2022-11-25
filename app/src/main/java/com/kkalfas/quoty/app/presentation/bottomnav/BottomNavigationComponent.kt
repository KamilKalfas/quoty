package com.kkalfas.quoty.app.presentation.bottomnav

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

@Composable
fun BottomNavigationComponent(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = MaterialTheme.colors.secondaryVariant,
    selectedNavigation: NavDestination?,
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
        items.forEach { item ->
            BottomNavigationItem(
                selectedContentColor = MaterialTheme.colors.secondaryVariant,
                unselectedContentColor = MaterialTheme.colors.secondary,
                icon = {
                    Icon(imageVector = item.icon, contentDescription = stringResource(item.titleId))
                },
                alwaysShowLabel = false,
                selected = selectedNavigation?.hierarchy?.any { it.route == item.screen.route } == true,
                onClick = { onNavigationSelected(item) }
            )
        }
    }
}
