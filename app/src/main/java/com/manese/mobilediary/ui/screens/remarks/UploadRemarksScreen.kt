package com.manese.mobilediary.ui.screens.remarks

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.manese.mobilediary.data.RemarksViewModel
import com.manese.mobilediary.models.RemarksDto
import com.manese.mobilediary.navigation.ROUT_HOMEWORK
import com.manese.mobilediary.navigation.ROUT_REMARKS
import com.manese.mobilediary.ui.theme.Blue01
import com.manese.mobilediary.ui.theme.Gold01
import com.manese.mobilediary.ui.theme.White01

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun UploadRemarksScreen(
    navController: NavController,
    viewModel: RemarksViewModel
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "Upload Remarks",
                        color = Gold01
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.navigate(ROUT_REMARKS)
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Gold01
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue01
                )
            )
        },
        containerColor = White01
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
                .background(White01)
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Remarks Title") },
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
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
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

                    viewModel.saveRemarks(
                        RemarksDto(
                            id = null,
                            classId = "CLASS_1", // or from input later
                            studentName = "Student A", // or real user data
                            teacherName = "Mr Smith",
                            comment = "$title\n$description",
                            createdAt = System.currentTimeMillis()
                        )
                    )

                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue01,
                    contentColor = Gold01
                )
            ) {
                Text("Upload remarks")
            }
        }
    }
}