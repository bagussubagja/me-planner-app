package com.mantequilla.devplanner.presentation.calendar

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mabn.calendarlibrary.core.CalendarTheme
import com.mantequilla.devplanner.R
import com.mantequilla.devplanner.domain.item.TaskItem
import com.mantequilla.devplanner.ui.theme.greenAccentDark
import com.mantequilla.devplanner.ui.theme.greenAccentLight
import com.mantequilla.devplanner.ui.theme.osFontFamily
import com.mantequilla.devplanner.ui.theme.pinkAccentDark
import com.mantequilla.devplanner.ui.theme.pinkAccentLight
import com.mantequilla.devplanner.ui.theme.yellowAccentDark
import com.mantequilla.devplanner.ui.theme.yellowAccentLight
import com.mantequilla.devplanner.utils.Converter

@Composable
fun CalendarScreen(
    navHostController: NavHostController,
    context: Context,
    paddingValues: PaddingValues
) {
    val calendarViewModel: CalendarViewModel = hiltViewModel()
    val state by calendarViewModel.state.collectAsState()
    var today by remember { mutableStateOf(Converter.getCurrentDate()) }
    val isPlaying by remember { mutableStateOf(true) }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty))
    val progress by animateLottieCompositionAsState(composition, isPlaying = isPlaying)
    LaunchedEffect(key1 = today) {
        calendarViewModel.refreshData(today)
    }
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        item {
            com.mabn.calendarlibrary.ExpandableCalendar(
                onDayClick = { newDate ->
                    today = newDate.toString()
                },
                theme = CalendarTheme(
                    backgroundColor = Color.White,
                    selectedDayBackgroundColor = greenAccentDark,
                    dayBackgroundColor = greenAccentLight,
                    dayShape = RoundedCornerShape(12.dp),
                    dayValueTextColor = greenAccentDark,
                    headerBackgroundColor = Color.White,
                    headerTextColor = Color.Black,
                    selectedDayValueTextColor = Color.White,
                    weekDaysTextColor = Color.Black
                )
            )
        }
        when (state) {
            is CalendarState.ErrorFetchData -> {
                item {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Task Not Found", style = TextStyle(fontFamily = osFontFamily))
                    }
                }
            }

            is CalendarState.Loading -> {
                item {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            color = greenAccentDark
                        )
                    }
                }
            }

            is CalendarState.SuccessFetchData -> {
                val taskByDate = (state as CalendarState.SuccessFetchData<List<TaskItem>>).data
                if (taskByDate.isEmpty()) {
                    item {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            LottieAnimation(
                                composition = composition,
                                progress = { progress },
                                modifier = Modifier
                                    .height(250.dp)
                                    .fillMaxWidth(),
                                alignment = Alignment.Center
                            )
                            Text(
                                text = "Task not found",
                                modifier = Modifier,
                                style = TextStyle(fontFamily = osFontFamily)
                            )
                        }
                    }
                } else {
                    items(taskByDate) { task ->
                        CardTodoList(
                            priority = task.priority!!,
                            title = task.title!!,
                            time = task.time!!,
                            tag = task.tag!!
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CardTodoList(
    priority: String,
    title: String,
    time: String,
    tag: List<String?>
) {
    fun getBackgroundColor(priority: String): Color {
        return when (priority) {
            "High Priority" -> pinkAccentLight
            "Medium Priority" -> yellowAccentLight
            "Low Priority" -> greenAccentLight
            else -> throw IllegalArgumentException("Invalid priority: $priority")
        }
    }

    fun getAccentColor(priority: String): Color {
        return when (priority) {
            "High Priority" -> pinkAccentDark
            "Medium Priority" -> yellowAccentDark
            "Low Priority" -> greenAccentDark
            else -> throw IllegalArgumentException("Invalid priority: $priority")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(getBackgroundColor(priority), shape = RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(80.dp)
                    .width(5.dp)
                    .background(getAccentColor(priority), shape = RoundedCornerShape(12.dp)),
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
                            color = getAccentColor(priority)
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
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .background(getAccentColor(priority), shape = RoundedCornerShape(12.dp))
                    ) {
                        Text(
                            text = tag[0]!!,
                            modifier = Modifier.padding(6.dp),
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = osFontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    if (tag.size > 1) {
                        Text(
                            text = "+ ${tag.size - 1} more", style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = osFontFamily,
                                fontWeight = FontWeight.Medium,
                                color = getAccentColor(priority)
                            )
                        )
                    }
                }
            }
        }
    }
}