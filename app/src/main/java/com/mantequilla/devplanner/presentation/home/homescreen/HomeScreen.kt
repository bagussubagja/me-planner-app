package com.mantequilla.devplanner.presentation.home.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mantequilla.devplanner.ui.theme.greenAccentDark
import com.mantequilla.devplanner.ui.theme.greenAccentLight
import com.mantequilla.devplanner.ui.theme.osFontFamily
import com.mantequilla.devplanner.ui.theme.pinkAccentDark
import com.mantequilla.devplanner.ui.theme.pinkAccentLight
import com.mantequilla.devplanner.ui.theme.yellowAccentDark
import com.mantequilla.devplanner.ui.theme.yellowAccentLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(12.dp)
        ) {
            HeaderSection(navHostController)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Today:",
                    style = TextStyle(
                        fontFamily = osFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = "Tuesday, 14 November 2023",
                    style = TextStyle(
                        fontFamily = osFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
            }
            CardTodoList(
                greenAccentLight,
                greenAccentDark,
                greenAccentDark,
                "Low Priority",
                "Guitar lesson with Jacob in the city center",
                "10:30 AM"
            )
            CardTodoList(
                pinkAccentLight,
                pinkAccentDark,
                pinkAccentDark,
                "High Priority",
                "Biology Lecture",
                "12:00 AM"
            )
            CardTodoList(
                yellowAccentLight,
                yellowAccentDark,
                yellowAccentDark,
                "Medium Priority",
                "Biology Lecture",
                "12:00 AM"
            )
        }
    }
}

@Composable
private fun CardTodoList(
    backgroundColor: Color,
    accentColor: Color,
    priorityTextColor: Color,
    priority: String,
    title: String,
    time: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(80.dp)
                    .width(5.dp)
                    .background(accentColor, shape = RoundedCornerShape(12.dp)),
                content = {}
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = priority,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = osFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = priorityTextColor
                        )
                    )
                    Text(
                        text = time,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = osFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray
                        )
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = osFontFamily,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .background(accentColor, shape = RoundedCornerShape(12.dp))
                ) {
                    Text(
                        text = "Mobile Developer",
                        modifier = Modifier.padding(6.dp),
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = osFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun HeaderSection(navHostController: NavHostController) {
    Text(
        text = "To do List",
        style = TextStyle(
            fontFamily = osFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
    )
}