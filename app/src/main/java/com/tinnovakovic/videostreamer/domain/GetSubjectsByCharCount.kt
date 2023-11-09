package com.tinnovakovic.videostreamer.domain

import com.tinnovakovic.videostreamer.data.StreamerRepo
import com.tinnovakovic.videostreamer.data.models.Subject
import javax.inject.Inject

class GetSubjectsByCharCount @Inject constructor(private val streamerRepo: StreamerRepo) {

    fun execute(charCount: Int = CHAR_COUNT_LIMIT): List<Subject> {

        return streamerRepo.getSubjects().filter { it.title.length >= charCount }

    }



}

private const val CHAR_COUNT_LIMIT = 2