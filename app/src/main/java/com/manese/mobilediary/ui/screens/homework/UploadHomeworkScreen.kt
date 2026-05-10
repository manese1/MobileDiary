package com.manese.mobilediary.ui.screens.homework

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.manese.mobilediary.data.HomeworkViewModel
import com.manese.mobilediary.models.HomeworkDto
import com.manese.mobilediary.navigation.ROUT_HOMEWORK
import com.manese.mobilediary.navigation.ROUT_REGISTER
import com.manese.mobilediary.ui.theme.Blue01
import com.manese.mobilediary.ui.theme.Gold01
import com.manese.mobilediary.ui.theme.White01

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun UploadHomeworkScreen(
    viewModel: HomeworkViewModel,
    navController: NavController
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var classId by remember { mutableStateOf("") }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "Upload Homework",
                        color = Gold01
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.navigate(ROUT_HOMEWORK)
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Gold01
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue01
                )
            )
        },
        containerColor = White01
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
                .background(White01)
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Homework Title") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Gold01,
                    focusedLabelColor = Gold01,

                    unfocusedBorderColor = Blue01,
                    unfocusedLabelColor = Blue01,

                    cursorColor = Gold01
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Gold01,
                    focusedLabelColor = Gold01,

                    unfocusedBorderColor = Blue01,
                    unfocusedLabelColor = Blue01,

                    cursorColor = Gold01
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {

                    viewModel.saveHomework(
                        HomeworkDto(
                            title = title,
                            description = description,
                            classId = classId,
                            teacherName = "",
                            isCompleted = false,
                            isConfirmed = false
                        )
                    )

                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue01,
                    contentColor = Gold01
                )
            ) {
                Text("Upload Homework")
            }
        }
    }
}