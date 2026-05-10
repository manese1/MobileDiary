package com.manese.mobilediary.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*

import com.manese.mobilediary.models.HomeworkDto

open class HomeworkViewModel : ViewModel() {

    private val database =
        FirebaseDatabase.getInstance().reference

    var homeworkList = mutableStateListOf<HomeworkDto>()
        private set

    init {
        fetchHomework()
    }

    fun saveHomework(homework: HomeworkDto) {

        val homeworkId =
            database.child("homework").push().key

        if (homeworkId != null) {

            database.child("homework")
                .child(homeworkId)
                .setValue(homework)
        }
    }

    private fun fetchHomework() {

        database.child("homework")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    homeworkList.clear()

                    snapshot.children.forEach { snap ->

                        val homework =
                            snap.getValue(HomeworkDto::class.java)

                        if (homework != null) {
                            homeworkList.add(homework)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }
}