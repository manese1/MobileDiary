package com.manese.mobilediary.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase

@Composable
fun CreateClassScreen(
    navController: NavController
) {

    var className by remember { mutableStateOf("") }
    var teacherUid by remember { mutableStateOf("") }

    val database = FirebaseDatabase.getInstance().reference

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Create Class",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = className,
            onValueChange = { className = it },
            label = { Text("Class Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = teacherUid,
            onValueChange = { teacherUid = it },
            label = { Text("Class Teacher UID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                val classId = database.child("classes").push().key

                val classMap = mapOf(
                    "className" to className,
                    "teacherId" to teacherUid
                )

                if (classId != null) {
                    database.child("classes")
                        .child(classId)
                        .setValue(classMap)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Class")
        }
    }
}