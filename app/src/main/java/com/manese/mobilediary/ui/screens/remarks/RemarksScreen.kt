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

data class Remark(
    val student: String,
    val comment: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemarksScreen(
    navController: NavController,
    role: String = "STUDENT"
) {

    var remarks by remember {
        mutableStateOf(
            listOf(
                Remark("John Doe", "Good performance"),
                Remark("John Doe", "Needs improvement in math")
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
                    selected = false,
                    onClick = { navController.navigate(ROUT_ANNOUNCEMENTS) },
                    icon = { Icon(Icons.Default.Notifications, null) },
                    label = { Text("Announcements") }
                )

                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Default.Comment, null) },
                    label = { Text("Remarks") }
                )
            }
        },

        floatingActionButton = {
            if (role == "TEACHER") {
                FloatingActionButton(onClick = {
                    remarks = remarks + Remark("Student", "New remark")
                }) {
                    IconButton(onClick = {navController.navigate(ROUT_UPLOAD_REMARKS)}) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Remark")}
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