package com.manese.mobilediary.ui.screens.announcements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.manese.mobilediary.data.AnnouncementViewModel
import com.manese.mobilediary.models.Announcement
import com.manese.mobilediary.navigation.ROUT_HOME
import com.manese.mobilediary.navigation.ROUT_UPLOAD_ANNOUNCEMENT
import com.manese.mobilediary.ui.theme.Blue01
import com.manese.mobilediary.ui.theme.Gold01
import com.manese.mobilediary.ui.theme.White01
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnnouncementScreen(
    navController: NavController,
    viewModel: AnnouncementViewModel,
    userName: String = "User",
    role: String = "TEACHER"
) {

    val announcements by viewModel.announcements.collectAsState()

    Scaffold(

        // 🔝 TOP APP BAR
        topBar = {
            TopAppBar(

                title = {
                    Text(
                        text = "Announcements",
                        color = Gold01
                    )
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

        // ➕ FAB (TEACHER ONLY)
        floatingActionButton = {

            if (role == "TEACHER") {

                FloatingActionButton(

                    onClick = {
                        navController.navigate(ROUT_UPLOAD_ANNOUNCEMENT)
                    },

                    containerColor = Gold01,
                    contentColor = Blue01

                ) {

                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Upload Announcement"
                    )
                }
            }
        },

        containerColor = White01

    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(announcements) { item ->

                AnnouncementCard(
                    announcement = item
                )
            }
        }
    }
}

@Composable
fun AnnouncementCard(announcement: Announcement) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = announcement.title,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = announcement.message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnnouncementScreenPreview() {

    val fakeViewModel = object : AnnouncementViewModel() {

        private val fakeData = MutableStateFlow(
            listOf(
                Announcement("School Reopening", "School opens Monday", "ALL", ""),
                Announcement("Math Class Update", "Bring calculators", "CLASS", "8A")
            )
        )

        override val announcements = fakeData
    }

    MaterialTheme {
        AnnouncementScreen(rememberNavController(), viewModel = fakeViewModel)
    }
}