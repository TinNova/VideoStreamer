package com.tinnovakovic.videostreamer.data.local_api

import com.tinnovakovic.videostreamer.data.models.Lesson
import com.tinnovakovic.videostreamer.data.models.Subject

interface StreamerLocalJson {
    fun getSubjects(): List<Subject>
    fun getLessons(): List<Lesson>
}