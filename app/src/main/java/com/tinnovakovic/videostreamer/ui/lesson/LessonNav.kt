package com.tinnovakovic.videostreamer.ui.lesson

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tinnovakovic.videostreamer.Destination

fun NavGraphBuilder.lessonScreen() {
    composable(route = "${Destination.Lesson.name}/$LESSON_PATH") {
        val viewModel = hiltViewModel<LessonViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        LessonScreen(
            uiState = uiState,
            viewModel = viewModel
        )
    }
}

const val LESSON_PATH = "{subjectTitle}"
