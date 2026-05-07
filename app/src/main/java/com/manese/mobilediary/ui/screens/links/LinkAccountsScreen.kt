package com.manese.mobilediary.ui.screens.links

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase

@Composable
fun LinkAccountsScreen(
    navController: NavController
) {

    var parentUid by remember { mutableStateOf("") }
    var studentUid by remember { mutableStateOf("") }

    val database = FirebaseDatabase.getInstance().reference

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Link Parent to Student",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = parentUid,
            onValueChange = { parentUid = it },
            label = { Text("Parent UID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = studentUid,
            onValueChange = { studentUid = it },
            label = { Text("Student UID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                val linkMap = mapOf(
                    "studentId" to studentUid
                )

                database.child("parentStudentLinks")
                    .child(parentUid)
                    .setValue(linkMap)

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Link Accounts")
        }
    }
}