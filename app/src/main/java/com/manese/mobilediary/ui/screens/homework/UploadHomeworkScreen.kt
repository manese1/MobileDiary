package com.manese.mobilediary.ui.screens.homework

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.manese.mobilediary.ui.theme.Blue01
import com.manese.mobilediary.ui.theme.Gold01

@Composable
fun UploadHomeworkScreen(
    navController: NavController
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var classId by remember { mutableStateOf("") }

    val database = FirebaseDatabase.getInstance().reference
    val teacherId = FirebaseAuth.getInstance().currentUser?.uid

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Upload Homework",
            style = MaterialTheme.typography.headlineMedium,
            color = Gold01
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Homework title
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Homework Title") },
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

        // Homework description
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Homework Description") },
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

        // Class ID
        OutlinedTextField(
            value = classId,
            onValueChange = { classId = it },
            label = { Text("Class ID") },
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

                val homeworkId =
                    database.child("homework").push().key

                val homeworkMap = mapOf(
                    "id" to homeworkId,
                    "title" to title,
                    "description" to description,
                    "classId" to classId,
                    "teacherId" to teacherId
                )

                if (homeworkId != null) {

                    database.child("homework")
                        .child(homeworkId)
                        .setValue(homeworkMap)
                }
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