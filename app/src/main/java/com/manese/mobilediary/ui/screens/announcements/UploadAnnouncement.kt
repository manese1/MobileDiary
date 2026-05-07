package com.manese.mobilediary.ui.screens.announcements

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase

@Composable
fun UploadAnnouncementScreen(
    navController: NavController
) {

    var title by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var classId by remember { mutableStateOf("") }

    var scope by remember { mutableStateOf("GLOBAL") }

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

        // Title
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Announcement Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Message
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Announcement Message") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Scope Selector
        Text("Announcement Scope")

        Row {

            Row(verticalAlignment = Alignment.CenterVertically) {

                RadioButton(
                    selected = scope == "GLOBAL",
                    onClick = {
                        scope = "GLOBAL"
                    }
                )

                Text("Global")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {

                RadioButton(
                    selected = scope == "CLASS",
                    onClick = {
                        scope = "CLASS"
                    }
                )

                Text("Class")
            }
        }

        // Class ID only if CLASS selected
        if (scope == "CLASS") {

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = classId,
                onValueChange = { classId = it },
                label = { Text("Class ID") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                val announcementId =
                    database.child("announcements").push().key

                val announcementMap = mapOf(
                    "id" to announcementId,
                    "title" to title,
                    "message" to message,
                    "scope" to scope,
                    "classId" to classId
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