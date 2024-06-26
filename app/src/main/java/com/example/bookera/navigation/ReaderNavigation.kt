package com.example.bookera.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookera.screens.ReaderSplashScreen
import com.example.bookera.screens.details.BookDetailsScreen
import com.example.bookera.screens.home.Home
import com.example.bookera.screens.home.HomeScreenViewModel
import com.example.bookera.screens.login.ReaderLoginScreen
import com.example.bookera.screens.search.BooksSearchViewModel
import com.example.bookera.screens.search.SearchScreen
import com.example.bookera.screens.stats.ReaderStatsScreen
import com.example.bookera.screens.update.BookUpdateScreen


@Composable
fun  ReaderNavigation() {
    /* will allow us to be able to put together all the screens
    and navigate between them */
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ReaderScreens.SplashScreen.name
    ) {

        composable(ReaderScreens.SplashScreen.name) {
            ReaderSplashScreen(navController = navController)//backstack entry
        }

        composable(ReaderScreens.ReaderHomeScreen.name) {
            val homeViewModel = hiltViewModel<HomeScreenViewModel>()
            Home(navController = navController, viewModel = homeViewModel)
        }
        composable(ReaderScreens.LoginScreen.name) {
            ReaderLoginScreen(navController = navController)
        }
        composable(ReaderScreens.ReaderStatsScreen.name) {
            val homeViewModel = hiltViewModel<HomeScreenViewModel>()
            ReaderStatsScreen(navController = navController, viewModel = homeViewModel)
        }
        composable(ReaderScreens.SearchScreen.name) {
            val searchViewModel = hiltViewModel<BooksSearchViewModel>()
            SearchScreen(navController = navController)
        }
        composable(ReaderScreens.ReaderStatsScreen.name) {
            val homeViewModel = hiltViewModel<HomeScreenViewModel>()
            ReaderStatsScreen(navController = navController, viewModel = homeViewModel)
        }

        val detailName = ReaderScreens.DetailScreen.name
        composable("$detailName/{bookId}", arguments = listOf(navArgument("bookId") {
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("bookId").let {

                BookDetailsScreen(navController = navController, bookId = it.toString())
            }
        }

        val updateName = ReaderScreens.UpdateScreen.name
        composable("$updateName/{bookItemId}",
            arguments = listOf(navArgument("bookItemId") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("bookItemId").let {
                BookUpdateScreen(navController = navController, bookItemId = it.toString())
            }
        }
    }
}