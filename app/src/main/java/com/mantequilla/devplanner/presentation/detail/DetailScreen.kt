package com.mantequilla.devplanner.presentation.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mantequilla.devplanner.data.params.TaskParams
import com.mantequilla.devplanner.navigation.models.TaskModelNav
import com.mantequilla.devplanner.presentation.addtask.AddTaskState
import com.mantequilla.devplanner.presentation.addtask.AddTaskViewModel
import com.mantequilla.devplanner.ui.theme.osFontFamily
import com.mantequilla.devplanner.utils.Converter
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navHostController: NavHostController, taskParams: TaskModelNav) {
    var titleText by remember { mutableStateOf(taskParams.title) }
    var descText by remember { mutableStateOf(taskParams.desc) }
    var isExpanded by remember { mutableStateOf(false) }
    var priorityTask by remember { mutableStateOf(taskParams.priority) }
    var tagTask by remember { mutableStateOf(taskParams.tag?.joinToString(", ")) }
    val detailViewModel: DetailViewModel = hiltViewModel()
    val detailState by detailViewModel.state.collectAsState()
    LaunchedEffect(key1 = detailState) {
        when(detailState) {
            is DetailState.ErrorUpdateData -> {}
            is DetailState.Loading -> {}
            is DetailState.SuccessUpdateData -> {
                navHostController.popBackStack()
            }
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Detail Task")
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
            OutlinedTextField(
                value = titleText!!,
                onValueChange = { newText ->
                    titleText = newText
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                textStyle = TextStyle(fontFamily = osFontFamily, fontWeight = FontWeight.Normal),
                placeholder = {
                    Text(text = "Edit your task title here...")
                })
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = descText!!,
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
                    Text(text = "Edit your task description here...")
                })
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { newValue -> isExpanded = newValue },
                modifier = Modifier.background(Color.White)
            ) {
                OutlinedTextField(
                    value = priorityTask!!,
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
            OutlinedTextField(value = tagTask!!,
                onValueChange = { newText ->
                    tagTask = newText
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                textStyle = TextStyle(fontFamily = osFontFamily, fontWeight = FontWeight.Normal),
                placeholder = {
                    Text(text = "Insert your tags here, separated by commas..")
                })
            Spacer(modifier = Modifier.height(12.dp))
            ElevatedButton(
                onClick = {
                    val taskParamsData = TaskParams(
                        user_id = taskParams.user_id,
                        title = titleText!!,
                        desc = descText!!,
                        tag = tagTask!!.split(", "),
                        time = Converter.formatTime(LocalTime.now()),
                        date = Converter.getCurrentDate(),
                        priority = priorityTask!!
                    )
                    detailViewModel.updateTask(taskParamsData, "eq.${taskParams.id}")
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = titleText!!.isNotBlank()
                        && descText!!.isNotBlank()
                        && priorityTask != "Select task priority..."
                        && tagTask!!.isNotBlank()
            ) {
                Text(text = "Update Task", style = TextStyle(fontFamily = osFontFamily))
            }
        }
    }
}