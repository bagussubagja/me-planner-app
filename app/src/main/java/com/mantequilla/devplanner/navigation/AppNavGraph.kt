package com.mantequilla.devplanner.navigation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.mantequilla.devplanner.navigation.models.TaskModelNav
import com.mantequilla.devplanner.presentation.addtask.AddTaskScreen
import com.mantequilla.devplanner.presentation.calendar.CalendarScreen
import com.mantequilla.devplanner.presentation.detail.DetailScreen
import com.mantequilla.devplanner.presentation.home.BottomBarScreen
import com.mantequilla.devplanner.presentation.home.homescreen.HomeScreen
import com.mantequilla.devplanner.utils.AssetParamType

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    context: Context,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomBarScreen.Home.route,
        route = Graph.HOME
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navHostController = navHostController, paddingValues = paddingValues)
        }
        composable(route = BottomBarScreen.Calendar.route) {
            CalendarScreen(navHostController, context, paddingValues)
        }
        contentAppNavGraph(navHostController)
        authNavGraph(navHostController, context)
    }
}

fun NavGraphBuilder.contentAppNavGraph(navHostController: NavHostController) {
    navigation(route = Graph.CONTENT, startDestination = ContentScreen.AddTask.route) {
        composable(route = ContentScreen.AddTask.route) {
            AddTaskScreen(navHostController = navHostController)
        }
        composable(route = "${ContentScreen.DetailScreen.route}/{task}", arguments = listOf(
            navArgument("task") {
                type = AssetParamType()
            }
        )) {
            val task = it.arguments?.getParcelable<TaskModelNav>("task")
            DetailScreen(navHostController = navHostController, task!!)
        }
    }
}

sealed class ContentScreen(val route: String) {
    object AddTask : ContentScreen("ADD_TASK")
    object DetailScreen : ContentScreen("DETAIL_SCREEN")
}