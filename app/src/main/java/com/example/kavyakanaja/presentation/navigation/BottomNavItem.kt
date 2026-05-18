package com.example.kavyakanaja.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = Screen.Home.route,
        title = "Home",
        icon = Icons.Default.Home
    )
    object Search : BottomNavItem(
        route = Screen.Search.route,
        title = "Search",
        icon = Icons.Default.Search
    )
    object Favorites : BottomNavItem(
        route = Screen.Favorites.route,
        title = "Favorites",
        icon = Icons.Default.Favorite
    )
}
