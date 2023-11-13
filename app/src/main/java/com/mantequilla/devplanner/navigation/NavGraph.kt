package com.mantequilla.devplanner.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mantequilla.devplanner.presentation.auth.login.LoginScreen
import com.mantequilla.devplanner.presentation.auth.register.RegisterScreen
import com.mantequilla.devplanner.presentation.home.HomeScreen
import com.mantequilla.devplanner.presentation.splash.SplashScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navHostController = navHostController)
        }
        navigation(route = Screen.Auth.route, startDestination = Screen.Auth.LoginScreen.route) {
            composable(route = Screen.Auth.LoginScreen.route) {
                LoginScreen(navHostController = navHostController)
            }
            composable(route = Screen.Auth.RegisterScreen.route) {
                RegisterScreen(navHostController = navHostController)
            }
        }
        navigation(route = Screen.App.route, startDestination = Screen.App.HomeScreen.route) {
            composable(route = Screen.App.HomeScreen.route) {
                HomeScreen(navHostController = navHostController)
            }
        }
    }
}