package com.manese.mobilediary.ui.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.manese.mobilediary.navigation.*
import com.manese.mobilediary.ui.theme.Blue01
import com.manese.mobilediary.ui.theme.Gold01
import com.manese.mobilediary.ui.theme.White01

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Admin Management",
                        color = Gold01
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue01
                ),
                actions = {
                    IconButton(onClick = {navController.navigate(ROUT_LOGIN)}) {
                        Icon(imageVector = Icons.Default.Logout, contentDescription = "Logout Button", tint = Gold01)
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //Title text
            Text(
                text = "Would you like to:",
                style = MaterialTheme.typography.headlineSmall,
                color = Gold01
            )

            Spacer(modifier = Modifier.height(32.dp))

            //Cards
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                //Student Card
                Card(
                    colors = CardDefaults.cardColors(containerColor = White01),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(ROUT_REGISTER_STUDENT)
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = Blue01)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("Register Student", color = Blue01)
                    }
                }

                //Teacher Card
                Card(
                    colors = CardDefaults.cardColors(containerColor = White01),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(ROUT_TEACHER_REGISTER)
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.School, contentDescription = null, tint = Blue01)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("Register Teacher", color = Blue01)
                    }
                }

                //Parent Register
                Card(
                    colors = CardDefaults.cardColors(containerColor = White01),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(ROUT_REGISTER_PARENT)
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = Blue01)

                        Spacer(modifier = Modifier.width(16.dp))

                        Text("Register Parent", color = Blue01)
                    }
                }

                //Linking Card
                Card(
                    colors = CardDefaults.cardColors(containerColor = White01),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(ROUT_LINK_ACCOUNTS)
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Link, contentDescription = null, tint = Blue01)

                        Spacer(modifier = Modifier.width(16.dp))

                        Text("Link Parent & Student", color = Blue01)
                    }
                }

                Card(
                    colors = CardDefaults.cardColors(containerColor = White01),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(ROUT_CREATE_CLASS)
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Blue01)

                        Spacer(modifier = Modifier.width(16.dp))

                        Text("Create a class", color = Blue01)
                    }
                }

                Card(
                    colors = CardDefaults.cardColors(containerColor = White01),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(ROUT_ASSIGN_CLASS)
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Class, contentDescription = null, tint = Blue01)

                        Spacer(modifier = Modifier.width(16.dp))

                        Text("Assign Student to Class", color = Blue01)
                    }
                }
                Card(
                    colors = CardDefaults.cardColors(containerColor = White01),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(ROUT_UPLOAD_ANNOUNCEMENT)
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Comment, contentDescription = null, tint = Blue01)

                        Spacer(modifier = Modifier.width(16.dp))

                        Text("Upload Announcement", color = Blue01)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Admin note
            Text(
                text = "Admin Access Only",
                style = MaterialTheme.typography.bodySmall,
                color = Gold01
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen(navController = rememberNavController())
    }
}