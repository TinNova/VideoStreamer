package com.tinnovakovic.videostreamer.data

import com.tinnovakovic.videostreamer.data.models.Lesson
import com.tinnovakovic.videostreamer.data.models.Subject
import com.tinnovakovic.videostreamer.data.local_api.StreamerLocalJson
import com.tinnovakovic.videostreamer.data.models.CatFact
import com.tinnovakovic.videostreamer.data.remote_api.TemplateApi
import javax.inject.Inject

class StreamerRepo @Inject constructor(
    private val streamerLocalJson: StreamerLocalJson,
    private val templateApi: TemplateApi) {

    fun getSubjects(): List<Subject> = streamerLocalJson.getSubjects()
    fun getLesson(): List<Lesson> = streamerLocalJson.getLessons()

    suspend fun getCatFacts(): List<CatFact> = templateApi.getCatFacts()
}
