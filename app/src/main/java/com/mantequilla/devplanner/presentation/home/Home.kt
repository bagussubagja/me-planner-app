package com.mantequilla.devplanner.presentation.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mantequilla.devplanner.navigation.AppNavGraph
import com.mantequilla.devplanner.navigation.ContentScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navHostController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navHostController = navHostController) },
        floatingActionButton = {
            // Example FAB with navigationBarsPadding
            if (isHomeDestination(navHostController)) {
                FloatingActionButton(
                    onClick = { navHostController.navigate(ContentScreen.AddTask.route) },
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(16.dp) // You can adjust the padding as needed
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        },
        content = { innerPadding ->
            AppNavGraph(navHostController = navHostController, modifier = Modifier.padding(innerPadding))
        }
    )
}

@Composable
fun isHomeDestination(navHostController: NavHostController): Boolean {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    return currentDestination?.route == BottomBarScreen.Home.route
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Calendar,
        BottomBarScreen.Setting
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar {
            screens.forEach {
                AddItem(
                    screen = it,
                    currentDestination = currentDestination,
                    navController = navHostController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}