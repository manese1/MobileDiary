package com.manese.mobilediary.ui.screens.remarks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
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
import com.manese.mobilediary.ui.theme.Blue01
import com.manese.mobilediary.ui.theme.Gold01

data class Remark(
    val student: String,
    val comment: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemarksScreen(
    navController: NavController,
    userName: String = "User",
    role: String = "TEACHER"
) {

    var remarks by remember {
        mutableStateOf(
            listOf(
                Remark("", "Good performance"),
                Remark("", "Needs improvement in math")
            )
        )
    }

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Mobile Diary", color = Gold01)
                        Text("Your School Name", style = MaterialTheme.typography.labelSmall, color = Gold01)
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate("$ROUT_HOME/$userName/$role")
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

        bottomBar = {
            NavigationBar(
                containerColor = Blue01
            ) {

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("$ROUT_HOME/$userName/$role") },
                    icon = { Icon(Icons.Default.Home, null, tint = Gold01) },
                    label = { Text("Home", color = Gold01) }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUT_HOMEWORK) },
                    icon = { Icon(Icons.Default.Book, null, tint = Gold01) },
                    label = { Text("Homework", color = Gold01) }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(ROUT_ANNOUNCEMENTS) },
                    icon = { Icon(Icons.Default.Notifications, null, tint = Gold01) },
                    label = { Text("Announcements", color = Gold01) }
                )

                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Comment, null, tint = Gold01) },
                    label = { Text("Remarks", color = Gold01) }
                )
            }
        },

        floatingActionButton = {
            if (role == "TEACHER") {
                FloatingActionButton(onClick = {
                    remarks = remarks + Remark("Student", "New remark")
                }) {
                    IconButton(onClick = {navController.navigate(ROUT_UPLOAD_REMARKS)}) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Remark", tint = Blue01)}
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
            items(remarks) {
                RemarkCard(it)
            }
        }
    }
}

@Composable
fun RemarkCard(remark: Remark) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Student: ${remark.student}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(remark.comment)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RemarksPreview() {
    MaterialTheme {
        RemarksScreen(rememberNavController(), "TEACHER")
    }
}