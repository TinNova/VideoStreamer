package com.tinnovakovic.videostreamer.data

import com.tinnovakovic.videostreamer.data.models.Lesson
import com.tinnovakovic.videostreamer.data.models.Subject

interface StreamerRepo {

    fun getSubjects(): List<Subject>
    fun getLesson(): List<Lesson>
}