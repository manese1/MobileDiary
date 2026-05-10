package com.manese.mobilediary.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import kotlin.jvm.java
import com.manese.mobilediary.models.RemarksDto

open class RemarksViewModel : ViewModel() {

    private val database =
        FirebaseDatabase.getInstance().reference

    var remarksList = mutableStateListOf<RemarksDto>()
        private set

    init {
        fetchRemarks()
    }

    fun saveRemarks(remarks: RemarksDto) {

        val remarksId =
            database.child("remarks").push().key

        if (remarksId != null) {

            database.child("homework")
                .child(remarksId)
                .setValue(remarks)
        }
    }

    private fun fetchRemarks() {

        database.child("remarks")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    remarksList.clear()

                    snapshot.children.forEach { snap ->

                        val remarks =
                            snap.getValue(RemarksDto::class.java)

                        if (remarks != null) {
                            remarksList.add(remarks)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }
}