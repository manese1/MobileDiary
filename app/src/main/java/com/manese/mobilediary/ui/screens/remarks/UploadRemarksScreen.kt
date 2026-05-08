package com.manese.mobilediary.ui.screens.remarks

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.manese.mobilediary.ui.theme.Blue01
import com.manese.mobilediary.ui.theme.Gold01

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
            style = MaterialTheme.typography.headlineMedium,
            color = Gold01
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Student ID
        OutlinedTextField(
            value = studentId,
            onValueChange = { studentId = it },
            label = { Text("Student ID") },
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

        // Remark
        OutlinedTextField(
            value = remark,
            onValueChange = { remark = it },
            label = { Text("Remark") },
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
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Gold01,
                contentColor = Blue01
            )
        ) {
            Text("Upload Remark")
        }
    }
}

@Preview (showBackground = true)
@Composable
fun UploadRemarksPreview() {
    UploadRemarksScreen(rememberNavController())
}