package com.manese.mobilediary.models

data class RemarksDto(
    var id: String? = null,
    var classId: String? = null,
    var studentName: String? = null,
    var teacherName: String? = null,
    var comment: String? = null,
    var createdAt: Long? = null
)