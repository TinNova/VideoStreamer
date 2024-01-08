package com.tinnovakovic.videostreamer.ui.lesson

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tinnovakovic.videostreamer.composables.UiElement
import com.tinnovakovic.videostreamer.data.models.Component
import com.tinnovakovic.videostreamer.data.models.Primer
import com.tinnovakovic.videostreamer.data.models.Screen
import com.tinnovakovic.videostreamer.ui.lesson.LessonContract.UiEvents
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
    uiAction: (UiEvents) -> Unit,
) {

    Scaffold(
        topBar = {
            UiElement.LargeTopAppBar(
                label = uiState.subjectTitle,
                upIcon = Icons.Default.ArrowBack,
                upIconAction = {
                    uiAction(UiEvents.UpClicked)
                }
            )

        }
    ) { paddingValues ->

        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            PrimerScreen(uiState.primer.screens, uiAction)
            Timer(uiState.currentTime, uiState.isStartEnabled, uiAction)
        }

    }
}

@Composable
fun Timer(currentTime: Int, isStartEnabled: Boolean, uiAction: (UiEvents) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = currentTime.toString(), fontSize = 96.sp)
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            OutlinedButton(onClick = { uiAction(UiEvents.StartClicked(currentTime)) },
                enabled = isStartEnabled) {
                Text(text = "Start")
            }
            OutlinedButton(onClick = { uiAction(UiEvents.PauseClicked) }) {
                Text(text = "Pause")
            }
            OutlinedButton(onClick = { uiAction(UiEvents.StopClicked) }) {
                Text(text = "Stop")
            }
        }
    }
}

@Composable
fun PrimerScreen(screens: List<Screen>, uiAction: (UiEvents) -> Unit) {
    LazyColumn() {
        items(count = screens.size) { i ->
            screens[i].components.forEach {
                Component(it, uiAction)
            }
        }
    }
}

@Composable
fun Component(component: Component, uiAction: (UiEvents) -> Unit) {
    Column {
        when (component) {
            is Component.Container -> {
                component.components.forEach { Component(it, uiAction) }
            }

            is Component.Button -> {
                OutlinedButton(onClick = { uiAction(UiEvents.ComponentButtonClicked(component.onClick)) }) {
                    Text(text = component.text)
                }
            }

            is Component.Input -> {
                Text(text = component.hint + "INPUT FIELD")
            }

            is Component.Text -> {
                Text(text = component.text)
            }
        }
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
            cachedCurrentVideo = null,
            primer = Primer(emptyList(), emptyList()),
            currentTime = 0,
            isStartEnabled = false
        ),
        uiAction = {},
    )
}
