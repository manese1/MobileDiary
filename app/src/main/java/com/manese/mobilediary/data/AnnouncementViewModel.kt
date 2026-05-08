package com.manese.mobilediary.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.manese.mobilediary.models.Announcement
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

open class AnnouncementViewModel : ViewModel() {

    private val database = FirebaseDatabase.getInstance().reference

    private val _announcements = MutableStateFlow<List<Announcement>>(emptyList())
    open val announcements: StateFlow<List<Announcement>> = _announcements

    init {
        fetchAnnouncements()
    }

    open fun saveAnnouncement(announcement: Announcement) {
        val id = database.push().key ?: return
        database.child("announcements").child(id).setValue(announcement)
    }

    private fun fetchAnnouncements() {
        database.child("announcements")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = snapshot.children.mapNotNull { snap ->
                        snap.getValue(Announcement::class.java)
                    }

                    _announcements.value = list
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }
}