package com.example.kavyakanaja.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.kavyakanaja.presentation.favorites.FavoritesScreen
import com.example.kavyakanaja.presentation.home.HomeScreen
import com.example.kavyakanaja.presentation.poem_detail.PoemDetailScreen
import com.example.kavyakanaja.presentation.poet.PoetScreen
import com.example.kavyakanaja.presentation.search.SearchScreen
import com.example.kavyakanaja.presentation.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = Modifier.padding(paddingValues),
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.Home.route) {
            HomeScreen(
                onNavigateToPoemDetail = { poemId ->
                    navController.navigate(Screen.PoemDetail.createRoute(poemId))
                },
                onNavigateToPoemList = {
                    navController.navigate(Screen.PoemList.route)
                },
                onNavigateToFavorites = {
                    navController.navigate(Screen.Favorites.route)
                },
                onNavigateToSearch = {
                    navController.navigate(Screen.Search.route)
                }
            )
        }
        
        composable(route = Screen.PoemList.route) {
            // Placeholder for PoemListScreen
        }

        composable(
            route = Screen.PoemDetail.route,
            arguments = listOf(
                navArgument("poemId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            PoemDetailScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(route = Screen.PoetInfo.route) {
            PoetScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToPoemDetail = { poemId ->
                    navController.navigate(Screen.PoemDetail.createRoute(poemId))
                }
            )
        }

        composable(route = Screen.Favorites.route) {
            FavoritesScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToPoemDetail = { poemId ->
                    navController.navigate(Screen.PoemDetail.createRoute(poemId))
                }
            )
        }

        composable(route = Screen.Search.route) {
            SearchScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToPoemDetail = { poemId ->
                    navController.navigate(Screen.PoemDetail.createRoute(poemId))
                }
            )
        }
    }
}
