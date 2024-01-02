package com.tinnovakovic.videostreamer.ui.lesson

import com.tinnovakovic.videostreamer.data.StreamerRepo
import com.tinnovakovic.videostreamer.data.models.Lesson
import javax.inject.Inject

class GetLessonsUseCase @Inject constructor(
    private val streamerRepo: StreamerRepo
) {

    fun execute(subjectTitle: String): List<Lesson> {
        // we only have data for chemistry, all other subjects return an empty list
        return if (subjectTitle == CHEMISTRY) {
            streamerRepo.getLesson()
        } else {
            emptyList()
        }
    }
}

private const val CHEMISTRY = "Chemistry"
