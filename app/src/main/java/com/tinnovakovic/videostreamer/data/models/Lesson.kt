package com.tinnovakovic.videostreamer.data.models

data class Lesson(
    val id: Int,
    val title: String,
    val thumbnailPath: String,
    val videoPath: String,
    val progress: Double,
    val precedingLessonId: Int?,
    val succeedingLessonId: Int?
)