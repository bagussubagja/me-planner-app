package com.mantequilla.devplanner.navigation

import android.annotation.SuppressLint
import android.content.Context
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mantequilla.devplanner.presentation.auth.login.LoginScreen
import com.mantequilla.devplanner.presentation.auth.register.RegisterScreen

@SuppressLint("ComposableNavGraphInComposeScope")
fun NavGraphBuilder.authNavGraph (navHostController: NavHostController, context: Context) {
    navigation(route = Graph.AUTHENTICATION, startDestination = AuthScreen.Login.route) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(navHostController = navHostController, context = context)
        }
        composable(route = AuthScreen.Register.route) {
            RegisterScreen(navHostController = navHostController)
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object Login : AuthScreen(route = "LOGIN")
    data object Register : AuthScreen(route = "REGISTER")
}