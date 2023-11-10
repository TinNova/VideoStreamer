package com.tinnovakovic.videostreamer.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tinnovakovic.videostreamer.data.models.Lesson
import com.tinnovakovic.videostreamer.data.models.ScreenParent
import com.tinnovakovic.videostreamer.data.models.Subject
import com.tinnovakovic.videostreamer.remote.jsons.Jsons
import javax.inject.Inject

class StreamerApiImpl @Inject constructor(
    private val gson: Gson,
) : StreamerApi {
    override fun getLessons(): List<Lesson> {
        val listType = object : TypeToken<List<Lesson>>() {}.type
        return gson.fromJson(Jsons.lessons, listType)
    }

    override fun getSubjects(): List<Subject> {
        val listType = object : TypeToken<List<Subject>>() {}.type
        return gson.fromJson(Jsons.subjects, listType)
    }

    override fun getComponents(): ScreenParent {
        val listType = object : TypeToken<ScreenParent>() {}.type
        return gson.fromJson(Jsons.data, listType)
    }
}
