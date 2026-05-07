package com.manese.mobilediary.ui.screens.announcements

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
fun UploadAnnouncementScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
    ) {
        Text("Access Denied")
    }

    var title by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val database = FirebaseDatabase.getInstance().reference

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Upload Announcement",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Announcement Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Announcement Message") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                val announcementId =
                    database.child("announcements").push().key

                val announcementMap = mapOf(
                    "id" to announcementId,
                    "title" to title,
                    "message" to message
                )

                if (announcementId != null) {
                    database.child("announcements")
                        .child(announcementId)
                        .setValue(announcementMap)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Announcement")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UploadAnnouncementPreview() {
    MaterialTheme {
        UploadAnnouncementScreen(
            navController = rememberNavController()
        )
    }
}