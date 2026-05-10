package com.manese.mobilediary.models

data class HomeworkDto(
    var id: String? = null,
    var title: String? = null,
    var description: String? = null,
    var classId: String? = null,
    var teacherName: String? = null,
    var isCompleted: Boolean? = null,
    var isConfirmed: Boolean? = null,
    var createdAt: Long? = null
)