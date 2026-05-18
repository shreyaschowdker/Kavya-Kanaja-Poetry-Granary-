package com.example.kavyakanaja.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object PoemList : Screen("poem_list_screen")
    object PoemDetail : Screen("poem_detail_screen/{poemId}") {
        fun createRoute(poemId: String) = "poem_detail_screen/$poemId"
    }
    object PoetInfo : Screen("poet_info_screen")
    object Favorites : Screen("favorites_screen")
    object Search : Screen("search_screen")
}
