package com.tinnovakovic.videostreamer.ui.lesson

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tinnovakovic.videostreamer.composables.UiElement
import com.tinnovakovic.videostreamer.ui.lesson.LessonContract.UiState

@Composable
fun LessonScreen() {
    val viewModel = hiltViewModel<LessonViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LessonScreenContent(
        uiState = uiState,
        uiAction = viewModel::onUiEvent,
    )
}

@Composable
fun LessonScreenContent(
    uiState: UiState,
    uiAction: (LessonContract.UiEvents) -> Unit,
) {

    Scaffold(
        topBar = {
            UiElement.LargeTopAppBar(
                label = uiState.subjectTitle,
                upIcon = Icons.Default.ArrowBack,
                upIconAction = {
                    uiAction(LessonContract.UiEvents.UpClicked)
                }
            )

        }
    ) { paddingValues ->
        paddingValues
    }
}

@Preview
@Composable
fun LessonScreenContentPreview() {
    LessonScreenContent(
        uiState = UiState(
            subjectTitle = "Maths",
            lessons = emptyList(),
            upNavigateClicked = false,
            cachedCurrentVideo = null
        ),
        uiAction = {},
    )
}
