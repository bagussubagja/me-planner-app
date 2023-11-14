package com.mantequilla.devplanner.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mantequilla.devplanner.presentation.home.BottomBarScreen
import com.mantequilla.devplanner.presentation.home.homescreen.HomeScreen
import com.mantequilla.devplanner.presentation.home.settings.SettingScreen

@Composable
fun AppNavGraph (navHostController: NavHostController) {
   NavHost(navController = navHostController, startDestination = BottomBarScreen.Home.route, route = Graph.HOME) {
       composable(route = BottomBarScreen.Home.route) {
           HomeScreen(navHostController = navHostController)
       }
       composable(route = BottomBarScreen.Setting.route) {
           SettingScreen(navHostController = navHostController)
       }
   }
}