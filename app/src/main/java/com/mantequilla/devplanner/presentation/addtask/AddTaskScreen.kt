package com.mantequilla.devplanner.presentation.addtask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mantequilla.devplanner.data.params.TaskParams
import com.mantequilla.devplanner.ui.theme.osFontFamily
import com.mantequilla.devplanner.utils.Converter
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalDate
import java.time.LocalTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navHostController: NavHostController) {
    var titleText by remember { mutableStateOf("") }
    var descText by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }
    var priorityTask by remember { mutableStateOf("Select task priority...") }
    var tagTask by remember { mutableStateOf("") }
    val addTaskViewModel: AddTaskViewModel = hiltViewModel()
    val state by addTaskViewModel.state.collectAsState()
    LaunchedEffect(key1 = state) {
        when (state) {
            is AddTaskState.ErrorPostData -> {}
            is AddTaskState.Loading -> {}
            is AddTaskState.SuccessPostData -> {
                navHostController.popBackStack()
            }
        }
    }
    val calendarState = rememberSheetState()
    var calendarDate by remember { mutableStateOf(LocalDate.now()) }
    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date { date -> calendarDate = date })
    val timeState = rememberSheetState()
    var clockTime by remember { mutableStateOf(Converter.formatTime(LocalTime.now())) }
    ClockDialog(
        state = timeState,
        config = ClockConfig(
            is24HourFormat = true
        ),
        selection = ClockSelection.HoursMinutes { hours, minutes ->
            clockTime = String.format("%02d:%02d", hours, minutes)
        })
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Add Task")
                },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Localized description",
                        )
                    }
                },
            )
        },

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 8.dp)
        ) {
            OutlinedTextField(value = titleText,
                onValueChange = { newText ->
                    titleText = newText
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                textStyle = TextStyle(fontFamily = osFontFamily, fontWeight = FontWeight.Normal),
                placeholder = {
                    Text(text = "Add your task title here...")
                })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = descText,
                onValueChange = { newText ->
                    descText = newText
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                singleLine = false,
                maxLines = 5,
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                textStyle = TextStyle(fontFamily = osFontFamily, fontWeight = FontWeight.Normal),
                placeholder = {
                    Text(text = "Add your task description here...")
                })
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { newValue -> isExpanded = newValue },
                modifier = Modifier.background(Color.White)
            ) {
                OutlinedTextField(
                    value = priorityTask,
                    onValueChange = {},
                    readOnly = true,
                    textStyle = TextStyle(
                        fontFamily = osFontFamily,
                        fontWeight = FontWeight.Normal
                    ),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    DropdownMenuItem(modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                        text = { Text(text = "High Priority") },
                        onClick = {
                            priorityTask = "High Priority"
                            isExpanded = false
                        })
                    DropdownMenuItem(modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                        text = { Text(text = "Medium Priority") },
                        onClick = {
                            priorityTask = "Medium Priority"
                            isExpanded = false
                        })
                    DropdownMenuItem(modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                        text = { Text(text = "Low Priority") },
                        onClick = {
                            priorityTask = "Low Priority"
                            isExpanded = false
                        })
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = tagTask,
                onValueChange = { newText ->
                    tagTask = newText
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                textStyle = TextStyle(fontFamily = osFontFamily, fontWeight = FontWeight.Normal),
                placeholder = {
                    Text(text = "Insert your tags here, separated by commas...")
                })
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ElevatedButton(onClick = { calendarState.show() }) {
                    Text(text = "Date Picker")
                }
                Text(text = "$calendarDate")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ElevatedButton(onClick = { timeState.show() }) {
                    Text(text = "Time Picker")
                }
                Text(text = "$clockTime")
            }
            Spacer(modifier = Modifier.height(12.dp))
            ElevatedButton(
                onClick = {
                    val taskParams = TaskParams(
                        user_id = addTaskViewModel.userId.value,
                        title = titleText,
                        desc = descText,
                        tag = tagTask.split(", "),
                        time = clockTime,
                        date = calendarDate.toString(),
                        priority = priorityTask
                    )
                    addTaskViewModel.addTask(taskParams)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = titleText.isNotBlank()
                        && descText.isNotBlank()
                        && priorityTask != "Select task priority..."
                        && tagTask.isNotBlank()
            ) {
                Text(text = "Create Task", style = TextStyle(fontFamily = osFontFamily))
            }
        }
    }
}