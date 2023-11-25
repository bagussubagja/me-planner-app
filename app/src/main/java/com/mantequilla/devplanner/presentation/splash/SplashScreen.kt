package com.mantequilla.devplanner.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mantequilla.devplanner.navigation.AuthScreen
import com.mantequilla.devplanner.navigation.Graph

@Composable
fun SplashScreen (navHostController: NavHostController) {
    val splashViewModel: SplashViewModel = hiltViewModel()
    LaunchedEffect(splashViewModel) {
        kotlinx.coroutines.delay(3000)
        navHostController.navigate(Graph.AUTHENTICATION) {
            popUpTo(navHostController.graph.id) {
                inclusive = true
            }
        }
    }
    Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}