package com.tinnovakovic.videostreamer.data.local_api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tinnovakovic.videostreamer.data.models.Lesson
import com.tinnovakovic.videostreamer.data.models.Subject
import com.tinnovakovic.videostreamer.data.local_api.jsons.Jsons
import javax.inject.Inject

class StreamerLocalJsonImpl @Inject constructor(
    private val gson: Gson,
) : StreamerLocalJson {
    override fun getLessons(): List<Lesson> {
        val listType = object : TypeToken<List<Lesson>>() {}.type
        return gson.fromJson(Jsons.lessons, listType)
    }

    override fun getSubjects(): List<Subject> {
        val listType = object : TypeToken<List<Subject>>() {}.type
        return gson.fromJson(Jsons.subjects, listType)
    }
}
