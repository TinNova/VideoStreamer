package com.tinnovakovic.videostreamer.ui.lesson

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tinnovakovic.videostreamer.composables.UiElement

@Composable
fun LessonScreen(viewModel: LessonContract.ViewModel, onNavigateToHomeScreen: () -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LessonScreenContent(
        uiState = uiState,
        uiAction = viewModel::onUiEvent,
        onNavigateToHomeScreen = onNavigateToHomeScreen,
    )
}

@Composable
private fun LessonScreenContent(
    uiState: LessonContract.UiState,
    uiAction: (LessonContract.UiEvents) -> Unit,
    onNavigateToHomeScreen: () -> Unit
) {

    Scaffold(
        topBar = {
            UiElement.LargeTopAppBar(
                label = uiState.subjectTitle,
                upIcon = Icons.Default.ArrowBack,
                upIconAction = {
                    uiAction(LessonContract.UiEvents.UpClicked)
                    onNavigateToHomeScreen.invoke()
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
        uiState = LessonContract.UiState(
            subjectTitle = "Maths",
            lessons = emptyList(),
            upNavigateClicked = false,
            cachedCurrentVideo = null
        ),
        uiAction = {},
        onNavigateToHomeScreen = {},
    )
}
