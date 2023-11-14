package com.mantequilla.devplanner.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mantequilla.devplanner.presentation.home.Home

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Graph.AUTHENTICATION,
        route = Graph.ROOT
    ) {
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
    const val CONTENT = "content"
}