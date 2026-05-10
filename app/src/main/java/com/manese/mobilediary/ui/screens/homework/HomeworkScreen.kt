package com.manese.mobilediary.ui.screens.homework

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.manese.mobilediary.data.HomeworkViewModel
import com.manese.mobilediary.navigation.ROUT_HOME
import com.manese.mobilediary.navigation.ROUT_REGISTER_STUDENT
import com.manese.mobilediary.navigation.ROUT_UPLOAD_HOMEWORK
import com.manese.mobilediary.ui.theme.Blue01
import com.manese.mobilediary.ui.theme.Gold01
import com.manese.mobilediary.models.HomeworkDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeworkScreen(
    navController: NavController,
    userName : String = "User",
    role: String = "TEACHER" // STUDENT, PARENT, TEACHER
) {

    val viewModel: HomeworkViewModel = viewModel()
    val homeworkList = viewModel.homeworkList


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Homework", color = Gold01)
                },
                colors = TopAppBarDefaults.topAppBarColors(Blue01),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate("$ROUT_HOME/$userName/$role")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Gold01
                        )
                    }
                }
            )
        },

        // 👨‍🏫 Teacher gets FAB (Add Homework)
        floatingActionButton = {
            if (role == "TEACHER") {

                FloatingActionButton(
                    onClick = {
                        navController.navigate(ROUT_UPLOAD_HOMEWORK)
                    },
                    containerColor = Gold01,
                    contentColor = Blue01
                ) {

                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Homework",
                        tint = Blue01
                    )
                }
            }
        }

    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(homeworkList) { hw ->

                HomeworkCard(
                    homework = hw,
                    role = role,
                    onUpdate = { updated ->

                        val index = homeworkList.indexOfFirst {
                            it.title == updated.title
                        }

                        if (index != -1) {
                            homeworkList[index] = updated
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun HomeworkCard(
    homework: HomeworkDto,
    role: String,
    onUpdate: (HomeworkDto) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "${homework.title}",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "${homework.description}")

            Spacer(modifier = Modifier.height(12.dp))

            // ROLE-BASED ACTIONS
            when (role) {

                "STUDENT" -> {
                    homework.isCompleted?.let {
                        Button(
                            onClick = {
                                onUpdate(homework.copy(isCompleted = true))
                            },
                            enabled = !it
                        ) {
                            Text(
                                if (homework.isCompleted == true) "Completed" else "Mark as Complete"
                            )
                        }
                    }
                }

                "PARENT" -> {
                    Button(
                        onClick = {
                            onUpdate(homework.copy(isConfirmed = true))
                        },
                        enabled = homework.isCompleted == true && !homework.isConfirmed!!
                    ) {
                        Text(
                            if (homework.isConfirmed == true) "Confirmed"
                            else "Confirm Completion"
                        )
                    }
                }

                "TEACHER" -> {
                    Column {

                        Text(
                            text = when {
                                homework.isConfirmed == true -> "✔ Confirmed by Parent"
                                homework.isCompleted == true -> "✔ Completed by Student"
                                else -> "⏳ Pending"
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(onClick = {}) {
                            Text("Review")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeworkStudentPreview() {
    MaterialTheme {
        HomeworkScreen(
            navController = androidx.navigation.compose.rememberNavController(),
            role = "STUDENT"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeworkTeacherPreview() {
    MaterialTheme {
        HomeworkScreen(
            navController = androidx.navigation.compose.rememberNavController(),
            role = "TEACHER"
        )
    }
}