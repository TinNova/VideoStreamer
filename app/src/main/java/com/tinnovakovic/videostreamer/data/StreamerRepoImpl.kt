package com.tinnovakovic.videostreamer.data

import com.tinnovakovic.videostreamer.data.models.Lesson
import com.tinnovakovic.videostreamer.data.models.Subject
import com.tinnovakovic.videostreamer.data.local_api.StreamerLocalJson
import javax.inject.Inject

class StreamerRepo @Inject constructor(private val streamerLocalJson: StreamerLocalJson) {

    fun getSubjects(): List<Subject> = streamerLocalJson.getSubjects()
    fun getLesson(): List<Lesson> = streamerLocalJson.getLessons()

}
