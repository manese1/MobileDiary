package com.manese.mobilediary.ui.screens.homework

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
fun UploadHomeworkScreen(navController: NavController) {
   Box(
       modifier = Modifier.fillMaxSize(),
       contentAlignment = Alignment.Center
   ) {
       Text("Access Denied")
   }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val database = FirebaseDatabase.getInstance().reference

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Upload Homework",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Homework Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Homework Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                val homeworkId = database.child("homework").push().key

                val homeworkMap = mapOf(
                    "id" to homeworkId,
                    "title" to title,
                    "description" to description
                )

                if (homeworkId != null) {
                    database.child("homework")
                        .child(homeworkId)
                        .setValue(homeworkMap)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Homework")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UploadHomeworkPreview() {
    MaterialTheme {
        UploadHomeworkScreen(
            navController = rememberNavController()
        )
    }
}