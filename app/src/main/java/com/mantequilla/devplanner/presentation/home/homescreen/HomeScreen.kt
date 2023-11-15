package com.mantequilla.devplanner.presentation.home.homescreen

import android.content.Context
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mantequilla.devplanner.domain.item.TaskItem
import com.mantequilla.devplanner.ui.theme.greenAccentDark
import com.mantequilla.devplanner.ui.theme.greenAccentLight
import com.mantequilla.devplanner.ui.theme.osFontFamily
import com.mantequilla.devplanner.ui.theme.pinkAccentDark
import com.mantequilla.devplanner.ui.theme.pinkAccentLight
import com.mantequilla.devplanner.ui.theme.yellowAccentDark
import com.mantequilla.devplanner.ui.theme.yellowAccentLight
import com.mantequilla.devplanner.utils.Converter
import com.mantequilla.devplanner.utils.PreferencesManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController, context: Context) {
    val preferencesManager = remember { PreferencesManager(context) }
    val homeViewModel: HomeScreenViewModel = hiltViewModel()
    val homeState by homeViewModel.state.collectAsState()
    val isLoading by homeViewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    Scaffold {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                homeViewModel.getTasksData(
                    "*",
                    "eq.${preferencesManager.getIdUserInfo(com.mantequilla.devplanner.utils.StorageKey.userId, "")}",
                    "eq.${com.mantequilla.devplanner.utils.Converter.Companion.getCurrentDate()}"
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .padding(12.dp)
            ) {
                item {
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
                            text = Converter.getCurrentDateFormatted(),
                            style = TextStyle(
                                fontFamily = osFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
                when (homeState) {
                    is HomeScreenState.ErrorFetchData -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Task not found")
                            }
                        }
                    }

                    is HomeScreenState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    is HomeScreenState.SuccessFetchData -> {
                        val tasks =
                            (homeState as HomeScreenState.SuccessFetchData<List<TaskItem>>).data
                        if (tasks.isEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "Task not found")
                                }
                            }
                        } else {
                            items(tasks) { task ->
                                CardTodoList(
                                    task.priority!!,
                                    task.title!!,
                                    task.time!!,
                                    task.tag!!
                                )
                            }
                        }
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

@Composable
private fun HeaderSection(navHostController: NavHostController) {
    Text(
        text = "Dev Planner",
        style = TextStyle(
            fontFamily = osFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
    )
}