package com.manese.mobilediary.ui.screens.announcements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.database.*
import com.manese.mobilediary.models.Announcements

@Composable
fun AnnouncementScreen(
    navController: NavController
) {

    val database =
        FirebaseDatabase.getInstance().reference

    var announcements by remember {
        mutableStateOf(listOf<Announcements>())
    }

    // 🔥 Fetch from Firebase
    LaunchedEffect(Unit) {

        database.child("announcements")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = mutableListOf<Announcements>()

                    for (snap in snapshot.children) {

                        val announcement =
                            snap.getValue(Announcements::class.java)

                        if (announcement != null) {
                            list.add(announcement)
                        }
                    }

                    announcements = list
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    // UI
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(announcements) { announcement ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = announcement.title,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text = announcement.message)
                }
            }
        }
    }
}

@Composable
fun AnnouncementCard(announcements: Announcements) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(announcements.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(announcements.message)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnnouncementStudentPreview() {
    MaterialTheme {
        AnnouncementScreen(
            navController = androidx.navigation.compose.rememberNavController()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnnouncementTeacherPreview() {
    MaterialTheme {
        AnnouncementScreen(
            navController = androidx.navigation.compose.rememberNavController()
        )
    }
}