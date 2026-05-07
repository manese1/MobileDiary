package com.manese.mobilediary.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import com.manese.mobilediary.navigation.ROUT_LOGIN

@Composable
fun ProfileScreen(
    navController: NavController,
    userName: String = "User",
    role: String = "STUDENT"
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            // 👤 Profile Picture Placeholder
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(60.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Name
            Text(
                text = userName,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Role
            Text(
                text = role,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Extra info (optional)
            when (role) {
                "STUDENT" -> Text("Class: Grade 8")
                "PARENT" -> Text("Linked Student: John Doe")
                "TEACHER" -> Text("Subject: Mathematics")
            }
        }

        // 🔴 Logout Button at Bottom
        Button(
            onClick = {
                navController.navigate(ROUT_LOGIN) {
                    popUpTo(0)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Text("Logout")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    MaterialTheme {
        ProfileScreen(
            navController = rememberNavController(),
            userName = "Richie",
            role = "STUDENT"
        )
    }
}