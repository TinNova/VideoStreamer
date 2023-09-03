package com.tinnovakovic.videostreamer.data

import com.tinnovakovic.videostreamer.data.models.Lesson
import com.tinnovakovic.videostreamer.data.models.Subject
import com.tinnovakovic.videostreamer.remote.StreamerApi
import javax.inject.Inject

class StreamerRepo @Inject constructor(private val streamerApi: StreamerApi) {

    fun getSubjects(): List<Subject> = streamerApi.getSubjects()
    fun getLesson(): List<Lesson> = streamerApi.getLessons()

}
