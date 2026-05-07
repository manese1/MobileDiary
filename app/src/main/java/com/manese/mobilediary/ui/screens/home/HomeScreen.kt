package com.manese.mobilediary.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.manese.mobilediary.R
import com.manese.mobilediary.navigation.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    userName: String = "User",
    role: String = "STUDENT" // STUDENT, PARENT, TEACHER
) {

    Scaffold(

        // 🔝 TOP BAR
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Mobile Diary")
                        Text(
                            text = "Your School Name",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                navigationIcon = {
                    Row(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // 👉 Replace with your app logo if you want
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = "App Logo"
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        // 👉 Replace with your school logo
                        Icon(
                            imageVector = Icons.Default.AccountBalance,
                            contentDescription = "School Logo"
                        )

                        // Example if using real images:
                        /*
                        Image(
                            painter = painterResource(R.drawable.app_logo),
                            contentDescription = "App Logo",
                            modifier = Modifier.size(32.dp)
                        )
                        */
                    }
                },

                actions = {
                    if (role == "TEACHER") {
                        IconButton(onClick = {navController.navigate(ROUT_REGISTER_STUDENT)}) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Student")
                        }
                    }
                }
            )
        },

        // 📱 BOTTOM NAVIGATION
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate(ROUT_HOME) },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUT_HOMEWORK) },
                    icon = { Icon(Icons.Default.Book, contentDescription = "Homework") },
                    label = { Text("Homework") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUT_ANNOUNCEMENTS) },
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Announcements") },
                    label = { Text("Announcement") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(navController.navigate("$ROUT_PROFILE/$userName/$role")) },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        }

    ) { paddingValues ->

        // 🧱 MAIN CONTENT
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
        ) {

            Text(
                text = "Welcome, $userName",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                DashboardCard(
                    title = "Announcements",
                    icon = Icons.Default.Notifications,
                    onClick = { navController.navigate(ROUT_ANNOUNCEMENTS) }
                )

                DashboardCard(
                    title = "Homework",
                    icon = Icons.Default.Book,
                    onClick = { navController.navigate(ROUT_HOMEWORK) }
                )

                DashboardCard(
                    title = "Remarks",
                    icon = Icons.Default.Comment,
                    onClick = { navController.navigate(ROUT_REMARKS) }
                )

                // 👨‍🏫 Teacher-only feature
                if (role == "TEACHER") {
                    DashboardCard(
                        title = "Manage Homework",
                        icon = Icons.Default.Edit,
                        onClick = { navController.navigate(ROUT_HOMEWORK) }
                    )
                }
            }
        }
    }
}

@Composable
fun DashboardCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            navController = rememberNavController(),
            userName = "Richie",
            role = "TEACHER"
        )
    }
}