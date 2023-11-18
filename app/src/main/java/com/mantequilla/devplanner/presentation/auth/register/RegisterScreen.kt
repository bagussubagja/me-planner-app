package com.mantequilla.devplanner.presentation.auth.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mantequilla.devplanner.data.params.AuthParams
import com.mantequilla.devplanner.navigation.Graph
import com.mantequilla.devplanner.ui.theme.osFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navHostController: NavHostController) {
    val registerViewModel: AuthRegisterViewModel = hiltViewModel()
    val state by registerViewModel.state.collectAsState()
    LaunchedEffect(key1 = state) {
        when (state) {
            is AuthRegisterState.Initial -> {}
            is AuthRegisterState.Loading -> {}
            is AuthRegisterState.RegisterFailed -> {}
            is AuthRegisterState.RegisterSuccess -> {
                navHostController.navigate(Graph.HOME)
            }
        }
    }
    var emailText by remember {
        mutableStateOf("")
    }
    var passwordText by remember {
        mutableStateOf("")
    }
    var visiblePassword by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Me Planner - Your daily assistant",
            fontFamily = osFontFamily,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Register",
            style = TextStyle(
                fontSize = 28.sp,
                fontFamily = osFontFamily,
                fontWeight = FontWeight.Bold
            ),
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = emailText,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { text ->
                emailText = text
            },
            placeholder = {
                Text(text = "Email Address")
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = passwordText,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { text ->
                passwordText = text
            },
            trailingIcon = {
                IconButton(onClick = {
                    visiblePassword = !visiblePassword
                }) {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "")
                }
            },
            visualTransformation = if (visiblePassword) VisualTransformation.None else PasswordVisualTransformation(),
            placeholder = {
                Text(text = "Password")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            val authParams = AuthParams(email = emailText, password = passwordText)
            registerViewModel.authRegister(authParams)
        }) {
            Text(text = "Register")
        }
        Box(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account?",
                    style = TextStyle(
                        fontFamily = osFontFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp
                    )
                )
                TextButton(onClick = {
                    navHostController.popBackStack()
                }) {
                    Text(text = "Login Here")
                }
            }
        }
    }
}