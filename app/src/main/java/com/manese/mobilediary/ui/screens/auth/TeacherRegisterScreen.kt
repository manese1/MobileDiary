package com.manese.mobilediary.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.manese.mobilediary.states.AuthState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manese.mobilediary.data.AuthViewModel
import com.manese.mobilediary.navigation.ROUT_LOGIN
import com.manese.mobilediary.ui.theme.Blue01
import com.manese.mobilediary.ui.theme.Gold01

@Composable
fun TeacherRegisterScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state by viewModel.loginState.collectAsState()

    LaunchedEffect(state) {
        if (state is AuthState.Success) {
            navController.popBackStack() // go back to login
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Register Teacher", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Gold01,
                unfocusedBorderColor = Blue01,
                focusedLabelColor = Gold01,
                unfocusedLabelColor = Blue01,
                cursorColor = Gold01
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Gold01,
                unfocusedBorderColor = Blue01,
                focusedLabelColor = Gold01,
                unfocusedLabelColor = Blue01,
                cursorColor = Gold01
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Gold01,
                unfocusedBorderColor = Blue01,
                focusedLabelColor = Gold01,
                unfocusedLabelColor = Blue01,
                cursorColor = Gold01
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.register(name, email, password, "TEACHER")
                navController.navigate(ROUT_LOGIN)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue01,
                contentColor = Gold01
            )
        ) {
            Text("Register")
        }

        TextButton(
            onClick = { navController.navigate(ROUT_LOGIN) }
        ) {
            Text("Go to login")
        }
    }
}