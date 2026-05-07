package com.manese.mobilediary.ui.screens.homework

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.manese.mobilediary.navigation.ROUT_REGISTER_STUDENT

// 🔹 Data model (temporary)
data class Homework(
    val title: String,
    val description: String,
    var isCompleted: Boolean = false,
    var isConfirmed: Boolean = false
)

@Composable
fun HomeworkScreen(
    navController: NavController,
    role: String = "STUDENT" // STUDENT, PARENT, TEACHER
) {

    // 🔹 Sample data (replace with Firebase later)
    var homeworkList by remember {
        mutableStateOf(
            listOf(
                Homework("Math Assignment", "Page 23 - Q1-10"),
                Homework("English Essay", "Write about your holiday"),
                Homework("Science Project", "Build a simple circuit")
            )
        )
    }

    Scaffold(

        // 👨‍🏫 Teacher gets FAB (Add Homework)
        floatingActionButton = {
            if (role == "TEACHER") {
                FloatingActionButton(
                    onClick = {
                        // TEMP: Add dummy homework
                        homeworkList = homeworkList + Homework(
                            "New Homework",
                            "Added by Teacher"
                        )
                    }
                ) {
                    IconButton(onClick = {navController.navigate(ROUT_REGISTER_STUDENT)}) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Homework")}
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
                        homeworkList = homeworkList.map {
                            if (it.title == updated.title) updated else it
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun HomeworkCard(
    homework: Homework,
    role: String,
    onUpdate: (Homework) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = homework.title,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = homework.description)

            Spacer(modifier = Modifier.height(12.dp))

            // 🔥 ROLE-BASED ACTIONS
            when (role) {

                "STUDENT" -> {
                    Button(
                        onClick = {
                            onUpdate(homework.copy(isCompleted = true))
                        },
                        enabled = !homework.isCompleted
                    ) {
                        Text(
                            if (homework.isCompleted) "Completed" else "Mark as Complete"
                        )
                    }
                }

                "PARENT" -> {
                    Button(
                        onClick = {
                            onUpdate(homework.copy(isConfirmed = true))
                        },
                        enabled = homework.isCompleted && !homework.isConfirmed
                    ) {
                        Text(
                            if (homework.isConfirmed) "Confirmed"
                            else "Confirm Completion"
                        )
                    }
                }

                "TEACHER" -> {
                    Column {

                        Text(
                            text = when {
                                homework.isConfirmed -> "✔ Confirmed by Parent"
                                homework.isCompleted -> "✔ Completed by Student"
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