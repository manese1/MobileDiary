package com.manese.mobilediary.ui.screens.announcements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.manese.mobilediary.data.AnnouncementViewModel
import com.manese.mobilediary.models.Announcement
import com.manese.mobilediary.navigation.ROUT_ANNOUNCEMENTS
import com.manese.mobilediary.navigation.ROUT_REGISTER
import com.manese.mobilediary.ui.theme.Blue01
import com.manese.mobilediary.ui.theme.Gold01
import com.manese.mobilediary.ui.theme.White01

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadAnnouncementScreen(
    viewModel: AnnouncementViewModel,
    navController: NavController
) {

    var title by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text(
                        text = "Upload Announcement",
                        color = Gold01
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.navigate(ROUT_REGISTER)
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
                text = "Post New Announcement",
                style = MaterialTheme.typography.headlineMedium,
                color = Blue01
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                value = title,
                onValueChange = { title = it },

                label = {
                    Text("Title")
                },

                modifier = Modifier.fillMaxWidth(),

                colors = TextFieldDefaults.colors(

                    focusedContainerColor = White01,
                    unfocusedContainerColor = White01,

                    focusedIndicatorColor = Gold01,
                    unfocusedIndicatorColor = Blue01,

                    focusedLabelColor = Gold01,
                    unfocusedLabelColor = Blue01,

                    cursorColor = Gold01
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = message,
                onValueChange = { message = it },

                label = {
                    Text("Message")
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),

                colors = TextFieldDefaults.colors(

                    focusedContainerColor = Gold01,
                    unfocusedContainerColor = Blue01,

                    focusedIndicatorColor = Gold01,
                    unfocusedIndicatorColor = Blue01,

                    focusedLabelColor = Gold01,
                    unfocusedLabelColor = Blue01,

                    cursorColor = Gold01
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(

                onClick = {

                    viewModel.saveAnnouncement(
                        Announcement(
                            title = title,
                            message = message,
                            scope = "ALL"
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

                Text("Post Announcement")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UploadAnnouncementPreview() {

    val fakeViewModel = object : AnnouncementViewModel() {

        override fun saveAnnouncement(announcement: Announcement) {
        }
    }

    MaterialTheme {

        UploadAnnouncementScreen(
            viewModel = fakeViewModel,
            navController = rememberNavController()
        )
    }
}