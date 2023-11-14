package com.mantequilla.devplanner.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mantequilla.devplanner.presentation.auth.login.LoginScreen
import com.mantequilla.devplanner.presentation.auth.register.RegisterScreen

@SuppressLint("ComposableNavGraphInComposeScope")
fun NavGraphBuilder.authNavGraph (navHostController: NavHostController) {
    navigation(route = Graph.AUTHENTICATION, startDestination = AuthScreen.Login.route) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(navHostController = navHostController)
        }
        composable(route = AuthScreen.Register.route) {
            RegisterScreen(navHostController = navHostController)
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object Register : AuthScreen(route = "REGISTER")
}