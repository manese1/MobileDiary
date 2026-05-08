package com.manese.mobilediary.models

data class Announcement(
    val title: String = "",
    val message: String = "",
    val scope: String = "ALL",
    val classId: String = ""
)