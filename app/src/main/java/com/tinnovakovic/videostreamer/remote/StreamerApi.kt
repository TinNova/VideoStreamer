package com.tinnovakovic.videostreamer.remote

import com.tinnovakovic.videostreamer.data.models.Lesson
import com.tinnovakovic.videostreamer.data.models.Subject

interface StreamerApi {

    fun getLessons(): List<Lesson>
    fun getSubjects(): List<Subject>
}