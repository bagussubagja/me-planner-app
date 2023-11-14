package com.mantequilla.devplanner.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomBarScreen(
        "home",
        "Home",
        Icons.Default.Home
    )
    object Setting: BottomBarScreen(
        "setting",
        "Setting",
        Icons.Default.Settings
    )
}
