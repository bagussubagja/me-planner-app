package com.mantequilla.devplanner.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mantequilla.devplanner.presentation.home.Home
import com.mantequilla.devplanner.utils.PreferencesManager
import com.mantequilla.devplanner.utils.StorageKey

@Composable
fun NavGraph(navHostController: NavHostController, context: Context) {
    val preferencesManager = remember { PreferencesManager(context) }
    val userLoginInfo by remember { mutableStateOf(preferencesManager.getUserLoginInfo(StorageKey.userLoginInfo, false)) }
    NavHost(
        navController = navHostController,
        startDestination = if (userLoginInfo) Graph.HOME else Graph.AUTHENTICATION,
        route = Graph.ROOT
    ) {
        authNavGraph(navHostController, context)
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