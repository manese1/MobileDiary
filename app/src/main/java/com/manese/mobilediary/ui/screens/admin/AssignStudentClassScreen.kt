package com.manese.mobilediary.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase

@Composable
fun AssignStudentClassScreen(
    navController: NavController
) {

    var studentUid by remember { mutableStateOf("") }
    var classId by remember { mutableStateOf("") }

    val database = FirebaseDatabase.getInstance().reference

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Assign Student to Class",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = studentUid,
            onValueChange = { studentUid = it },
            label = { Text("Student UID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = classId,
            onValueChange = { classId = it },
            label = { Text("Class ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                database.child("students")
                    .child(studentUid)
                    .child("classId")
                    .setValue(classId)

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Assign Student")
        }
    }
}