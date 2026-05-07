package com.manese.mobilediary.ui.screens.remarks

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun UploadRemarksScreen(
    navController: NavController
) {

    var studentId by remember { mutableStateOf("") }
    var remark by remember { mutableStateOf("") }

    val database = FirebaseDatabase.getInstance().reference
    val teacherId = FirebaseAuth.getInstance().currentUser?.uid

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Upload Remark",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Student ID
        OutlinedTextField(
            value = studentId,
            onValueChange = { studentId = it },
            label = { Text("Student ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Remark
        OutlinedTextField(
            value = remark,
            onValueChange = { remark = it },
            label = { Text("Remark") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                val remarkId =
                    database.child("remarks").push().key

                val remarkMap = mapOf(
                    "id" to remarkId,
                    "studentId" to studentId,
                    "teacherId" to teacherId,
                    "remark" to remark,
                    "timestamp" to System.currentTimeMillis()
                )

                if (remarkId != null) {

                    database.child("remarks")
                        .child(remarkId)
                        .setValue(remarkMap)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Remark")
        }
    }
}