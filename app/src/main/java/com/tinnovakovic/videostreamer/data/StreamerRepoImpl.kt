package com.tinnovakovic.videostreamer.data

import com.tinnovakovic.videostreamer.data.models.Lesson
import com.tinnovakovic.videostreamer.data.models.ScreenParent
import com.tinnovakovic.videostreamer.data.models.Subject
import com.tinnovakovic.videostreamer.remote.StreamerApi
import javax.inject.Inject

class StreamerRepoImpl @Inject constructor(private val streamerApi: StreamerApi) {

    fun getSubjects(): List<Subject> = streamerApi.getSubjects()
    fun getLesson(): List<Lesson> = streamerApi.getLessons()

    fun getParentComponent(): ScreenParent = streamerApi.getComponents()

}
