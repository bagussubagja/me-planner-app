package com.mantequilla.devplanner.presentation.calendar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mabn.calendarlibrary.core.CalendarTheme
import com.mantequilla.devplanner.ui.theme.greenAccentDark
import com.mantequilla.devplanner.ui.theme.greenAccentLight
import com.mantequilla.devplanner.ui.theme.osFontFamily

@Composable
fun CalendarScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        com.mabn.calendarlibrary.ExpandableCalendar(
            onDayClick = {
                Log.d("Print date", "$it")
            }
        )
        CardTodoList(
            backgroundColor = greenAccentLight,
            accentColor = greenAccentDark,
            priorityTextColor = greenAccentDark,
            priority = "High Priority",
            title = "Learn Jetpack Compose",
            time = "24:00"
        )
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