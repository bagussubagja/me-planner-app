package com.mantequilla.devplanner.navigation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash")
    object Auth : Screen("auth") {
        object LoginScreen: Screen("login")
        object RegisterScreen: Screen("register")
    }
    object App : Screen("home") {
        object HomeScreen: Screen("home-screen")
    }
}
