package com.example.bookera.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookera.screens.ReaderSplashScreen
import com.example.bookera.screens.home.Home
import com.example.bookera.screens.login.ReaderLoginScreen


@Composable
fun  ReaderNavigation() {
    /* will allow us to be able to put together all the screens
    and navigate between them */
        val navController = rememberNavController()
        NavHost(navController = navController,
            startDestination = ReaderScreens.SplashScreen.name ){

            composable(ReaderScreens.SplashScreen.name) {
                ReaderSplashScreen(navController = navController)//backstack entry
            }

            composable(ReaderScreens.ReaderHomeScreen.name) {
                Home(navController = navController)
            }
            composable(ReaderScreens.LoginScreen.name) {
                ReaderLoginScreen(navController = navController)
            }


        }

    }