package com.manese.mobilediary.ui.screens.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import com.manese.mobilediary.ui.theme.Blue01
import com.manese.mobilediary.ui.theme.Gold01
import com.manese.mobilediary.ui.theme.White01

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateClassScreen(
    navController: NavController
) {

    var className by remember { mutableStateOf("") }
    var teacherUid by remember { mutableStateOf("") }

    val database = FirebaseDatabase.getInstance().reference

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "Create Class",
                        color = Gold01
                    )
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue01
                )
            )
        },

        containerColor = White01

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
                .background(White01),

            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Create New Class",
                style = MaterialTheme.typography.headlineMedium,
                color = Blue01
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = className,
                onValueChange = { className = it },

                label = {
                    Text("Class Name")
                },

                modifier = Modifier.fillMaxWidth(),

                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Gold01,
                    focusedLabelColor = Gold01,

                    unfocusedBorderColor = Blue01,
                    unfocusedLabelColor = Blue01,

                    cursorColor = Gold01
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = teacherUid,
                onValueChange = { teacherUid = it },

                label = {
                    Text("Class Teacher UID")
                },

                modifier = Modifier.fillMaxWidth(),

                colors = OutlinedTextFieldDefaults.colors(

                    focusedBorderColor = Gold01,
                    focusedLabelColor = Gold01,

                    unfocusedBorderColor = Blue01,
                    unfocusedLabelColor = Blue01,

                    cursorColor = Gold01
                )
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

                modifier = Modifier.fillMaxWidth(),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue01,
                    contentColor = Gold01
                )

            ) {

                Text("Create Class")
            }
        }
    }
}