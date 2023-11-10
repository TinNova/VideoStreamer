package com.tinnovakovic.videostreamer.remote

import com.tinnovakovic.videostreamer.data.models.Lesson
import com.tinnovakovic.videostreamer.data.models.ScreenParent
import com.tinnovakovic.videostreamer.data.models.Subject

interface StreamerApi {
    fun getSubjects(): List<Subject>
    fun getLessons(): List<Lesson>
    fun getComponents(): ScreenParent
}