package com.mantequilla.devplanner.presentation.home.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mantequilla.devplanner.R
import com.mantequilla.devplanner.ui.theme.osFontFamily


@Composable
fun SettingScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder_avatar),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Bagus Subagja",
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = osFontFamily,
                fontWeight = FontWeight.Bold,
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "bagussubagja@email.com",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = osFontFamily,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        ElevatedButton(onClick = { /*TODO*/ }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "")
                Text(text = "Logout")
            }
        }
    }
}