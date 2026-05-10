package com.manese.mobilediary.ui.screens.teacher

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.manese.mobilediary.data.AuthViewModel
import com.manese.mobilediary.navigation.ROUT_LOGIN
import com.manese.mobilediary.navigation.ROUT_REGISTER
import com.manese.mobilediary.ui.theme.Blue01
import com.manese.mobilediary.ui.theme.Gold01
import com.manese.mobilediary.ui.theme.White01

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterStudentScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "Register Student",
                        color = Gold01
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(ROUT_REGISTER)}) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back to Management Screen", tint = Gold01)
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue01
                )
            )
        },

        containerColor = White01

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text("Register Student", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Student Name") },
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
                label = { Text("Student Email") },
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
                label = { Text("Temporary Password") },
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
                    viewModel.register(name, email, password, "STUDENT")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue01,
                    contentColor = Gold01
                )
            ) {
                Text("Register Student")
            }
        }
    }
}