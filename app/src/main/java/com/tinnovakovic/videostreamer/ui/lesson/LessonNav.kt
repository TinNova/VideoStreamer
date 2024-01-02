package com.tinnovakovic.videostreamer.ui.lesson

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tinnovakovic.videostreamer.Destination

fun NavGraphBuilder.lessonScreen(
    navController: NavController
) {
    composable(route = "${Destination.Lesson.name}/{subjectTitle}") {
        val viewModel = hiltViewModel<LessonViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        LessonScreen(
            uiState = uiState,
            viewModel = viewModel,
            onNavigateToHomeScreen = {
                navController.navigate(Destination.Home.name)
            }
        )
    }
}

