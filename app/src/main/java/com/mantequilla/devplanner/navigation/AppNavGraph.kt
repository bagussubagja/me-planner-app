package com.mantequilla.devplanner.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mantequilla.devplanner.presentation.addtask.AddTaskScreen
import com.mantequilla.devplanner.presentation.calendar.CalendarScreen
import com.mantequilla.devplanner.presentation.home.BottomBarScreen
import com.mantequilla.devplanner.presentation.home.homescreen.HomeScreen
import com.mantequilla.devplanner.presentation.home.settings.SettingScreen

@Composable
fun AppNavGraph (navHostController: NavHostController, modifier: Modifier, context: Context) {
   NavHost(navController = navHostController, startDestination = BottomBarScreen.Home.route, route = Graph.HOME) {
       composable(route = BottomBarScreen.Home.route) {
           HomeScreen(navHostController = navHostController, context)
       }
       composable(route = BottomBarScreen.Calendar.route) {
           CalendarScreen()
       }
       composable(route = BottomBarScreen.Setting.route) {
           SettingScreen(navHostController = navHostController)
       }
       authNavGraph(navHostController, context)
   }
}

fun NavGraphBuilder.contentAppNavGraph(navHostController: NavHostController) {
    navigation(route = Graph.CONTENT, startDestination = ContentScreen.AddTask.route) {
        composable(route = ContentScreen.AddTask.route) {
            AddTaskScreen(navHostController = navHostController)
        }
    }
}

sealed class ContentScreen(val route: String) {
    object AddTask: ContentScreen("ADD_TASK")
}