package com.manese.mobilediary.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.manese.mobilediary.navigation.ROUT_HOME
import com.manese.mobilediary.ui.theme.Blue01
import com.manese.mobilediary.data.AuthViewModel
import com.manese.mobilediary.states.AuthState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manese.mobilediary.navigation.ROUT_LOGIN
import com.manese.mobilediary.navigation.ROUT_REGISTER
import com.manese.mobilediary.navigation.ROUT_REGISTER_STUDENT

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {

    var username by remember { mutableStateOf("") } // kept (even if not used now)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state by viewModel.loginState.collectAsState()

    // 🔥 React to login result
    LaunchedEffect(state) {
        when (state) {
            is AuthState.Success -> {
                val success = state as AuthState.Success

                if (success.role == "ADMIN") {
                    navController.navigate(ROUT_REGISTER) {
                        popUpTo(ROUT_LOGIN) { inclusive = true }
                    }
                } else {
                    navController.navigate("$ROUT_HOME/${success.name}/${success.role}") {
                        popUpTo(ROUT_LOGIN) { inclusive = true }
                    }
                }
            }
            else -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1565C0),
                        Color(0xFF42A5F5)
                    )
                )
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            // Title
            Text(
                text = "Welcome To Mobile Diary",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            // Username
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    //  Call ViewModel instead of manual logic
                    viewModel.login(email, password)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            //  Feedback (no UI layout change, just extra elements)
            when (state) {
                is AuthState.Loading -> {
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator()
                }

                is AuthState.Error -> {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = (state as AuthState.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                else -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview () {
    LoginScreen(navController = rememberNavController())
}