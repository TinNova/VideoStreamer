package com.tinnovakovic.videostreamer.ui.home

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tinnovakovic.videostreamer.Destination

fun NavGraphBuilder.homeScreen(
    navController: NavController
) {
    composable(route = Destination.Home.name) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        HomeScreen(
            uiState = uiState,
            viewModel = viewModel,
            onNavigateToLessonScreen = { subjectTitle ->
                navController.navigate("${Destination.Lesson.name}/$subjectTitle")
            }
        )
    }
}
