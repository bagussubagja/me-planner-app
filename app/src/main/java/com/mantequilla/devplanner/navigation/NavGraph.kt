package com.mantequilla.devplanner.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mantequilla.devplanner.presentation.auth.login.LoginScreen
import com.mantequilla.devplanner.presentation.auth.register.RegisterScreen
import com.mantequilla.devplanner.presentation.home.Home
import com.mantequilla.devplanner.presentation.home.homescreen.HomeScreen
import com.mantequilla.devplanner.presentation.home.settings.SettingScreen
import com.mantequilla.devplanner.presentation.splash.SplashScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Graph.AUTHENTICATION, route = Graph.ROOT) {
        authNavGraph(navHostController)
        composable(route = Graph.HOME) {
            Home()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}