package com.mantequilla.devplanner.presentation.addtask

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mantequilla.devplanner.ui.theme.osFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navHostController: NavHostController) {
    var titleText by remember { mutableStateOf("") }
    var descText by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }
    var priorityTask by remember { mutableStateOf("Select task priority...") }
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
            OutlinedTextField(
                value = titleText,
                onValueChange = { newText ->
                    titleText = newText
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                maxLines = 1,
                textStyle = TextStyle(fontFamily = osFontFamily, fontWeight = FontWeight.Normal),
                placeholder = {
                    Text(text = "Add your task title here...")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = descText,
                onValueChange = { newText ->
                    descText = newText
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                singleLine = false,
                maxLines = 5,
                textStyle = TextStyle(fontFamily = osFontFamily, fontWeight = FontWeight.Normal),
                placeholder = {
                    Text(text = "Add your task description here...")
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { newValue -> isExpanded = newValue }) {
                OutlinedTextField(
                    value = priorityTask,
                    onValueChange = {},
                    readOnly = true,
                    textStyle = TextStyle(fontFamily = osFontFamily),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                ) {
                    DropdownMenuItem(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        text = { Text(text = "High Priority") },
                        onClick = {
                            priorityTask = "High Priority"
                            isExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        text = { Text(text = "Medium Priority") },
                        onClick = {
                            priorityTask = "Medium Priority"
                            isExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        text = { Text(text = "Low Priority") },
                        onClick = {
                            priorityTask = "Low Priority"
                            isExpanded = false
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            ElevatedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                enabled = titleText.isNotBlank() && descText.isNotBlank() && priorityTask != "Select task priority..."
            ) {
                Text(text = "Create Task")
            }
        }
    }
}