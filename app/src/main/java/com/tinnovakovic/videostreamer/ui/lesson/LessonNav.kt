package com.tinnovakovic.videostreamer.ui.lesson

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tinnovakovic.videostreamer.Destination

fun NavGraphBuilder.lessonScreen() {
    composable(route = "${Destination.Lesson.name}/$LESSON_PATH") {
        LessonScreen()
    }
}

const val LESSON_PATH = "{subjectTitle}"

