package com.kkalfas.quoty.app.presentation.bottomnav

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.kkalfas.quoty.R
import com.kkalfas.quoty.app.presentation.navigation.Screen

sealed class BottomNavItem(val icon: ImageVector, @StringRes val titleId: Int, val screen: Screen) {
    object Home : BottomNavItem(Icons.Default.Home, R.string.label_bottom_nav_home, Screen.Home)
    object Search : BottomNavItem(Icons.Default.Search, R.string.label_bottom_nav_search, Screen.Search)
    object Profile : BottomNavItem(Icons.Default.Person, R.string.label_bottom_nav_profile, Screen.Profile)
}
