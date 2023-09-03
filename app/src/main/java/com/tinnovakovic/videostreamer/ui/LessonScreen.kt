package com.tinnovakovic.videostreamer.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.tinnovakovic.videostreamer.composables.UiElement

@Composable
fun LessonScreen(viewModel: LessonContract.ViewModel, navController: NavHostController) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LessonScreenContent(
        uiState = uiState,
        uiAction = viewModel::onUiEvent,
        navController = navController
    )
}

@Composable
private fun LessonScreenContent(
    uiState: LessonContract.UiState,
    uiAction: (LessonContract.UiEvents) -> Unit,
    navController: NavController
) {

    Scaffold(
        topBar = {
            UiElement.LargeTopAppBar(
                label = uiState.subjects[0].title,
                upIcon = Icons.Default.ArrowBack,
                upIconAction = { uiAction(LessonContract.UiEvents.UpClicked(navController)) }
            )

        }
    ) { paddingValues ->
        paddingValues
    }
}
