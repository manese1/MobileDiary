package com.manese.mobilediary.ui.screens.announcements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.manese.mobilediary.navigation.*
import com.manese.mobilediary.ui.screens.homework.HomeworkScreen

data class Announcement(
    val title: String,
    val message: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementScreen(
    navController: NavController,
    role: String = "STUDENT"
) {

    var announcements by remember {
        mutableStateOf(
            listOf(
                Announcement("School Closed", "School will be closed on Friday"),
                Announcement("Sports Day", "Bring sports gear tomorrow")
            )
        )
    }

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Mobile Diary")
                        Text("Your School Name", style = MaterialTheme.typography.labelSmall)
                    }
                }
            )
        },

        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUT_HOME) },
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUT_HOMEWORK) },
                    icon = { Icon(Icons.Default.Book, null) },
                    label = { Text("Homework") }
                )

                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Notifications, null) },
                    label = { Text("Announcement") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUT_PROFILE) },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Profile") }
                )
            }
        },

        floatingActionButton = {
            if (role == "TEACHER") {
                FloatingActionButton(onClick = {
                    announcements = announcements + Announcement("New", "Posted by teacher")
                }) {
                    IconButton(onClick = {navController.navigate(ROUT_UPLOAD_ANNOUNCEMENT)}) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Announcement")}
                }
            }
        }

    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(announcements) {
                AnnouncementCard(it)
            }
        }
    }
}

@Composable
fun AnnouncementCard(announcement: Announcement) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(announcement.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(announcement.message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnnouncementStudentPreview() {
    MaterialTheme {
        AnnouncementScreen(
            navController = androidx.navigation.compose.rememberNavController(),
            role = "STUDENT"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnnouncementTeacherPreview() {
    MaterialTheme {
        AnnouncementScreen(
            navController = androidx.navigation.compose.rememberNavController(),
            role = "TEACHER"
        )
    }
}