package com.tinnovakovic.videostreamer.data

import com.tinnovakovic.videostreamer.data.models.Lesson
import com.tinnovakovic.videostreamer.data.models.Subject
import com.tinnovakovic.videostreamer.remote.StreamerApi
import javax.inject.Inject

class StreamerRepoImpl @Inject constructor(private val streamerApi: StreamerApi): StreamerRepo {

    override fun getSubjects(): List<Subject> = streamerApi.getSubjects()
    override fun getLesson(): List<Lesson> = streamerApi.getLessons()

}
