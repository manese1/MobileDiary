package com.manese.mobilediary.ui.screens.remarks

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.FirebaseDatabase

@Composable
fun UploadRemarksScreen(
    navController: NavController,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Access Denied")
    }

    var studentName by remember { mutableStateOf("") }
    var remark by remember { mutableStateOf("") }

    val database = FirebaseDatabase.getInstance().reference

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

        OutlinedTextField(
            value = studentName,
            onValueChange = { studentName = it },
            label = { Text("Student Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = remark,
            onValueChange = { remark = it },
            label = { Text("Remark") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                val remarkId = database.child("remarks").push().key

                val remarkMap = mapOf(
                    "id" to remarkId,
                    "studentName" to studentName,
                    "remark" to remark
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

@Preview(showBackground = true)
@Composable
fun UploadRemarksPreview() {
    MaterialTheme {
        UploadRemarksScreen(
            navController = rememberNavController()
        )
    }
}