package com.tinnovakovic.videostreamer.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tinnovakovic.videostreamer.composables.UiElement.CircularItem
import com.tinnovakovic.videostreamer.data.models.Components
import com.tinnovakovic.videostreamer.data.models.Subject
import com.tinnovakovic.videostreamer.ui.home.HomeContract.*
import com.tinnovakovic.videostreamer.ui.home.preview.HomeScreenContentPreviewParameter

@Composable
fun HomeScreen(viewModel: ViewModel, onNavigateToLessonScreen: (String) -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState,
        uiAction = viewModel::onUiEvent,
        onNavigateToLessonScreen = onNavigateToLessonScreen
    )
}

@Composable
private fun HomeScreenContent(
    uiState: UiState,
    uiAction: (UiEvents) -> Unit,
    onNavigateToLessonScreen: (String) -> Unit
) {

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

            uiState.screenParent?.screen?.forEach {

                it.component.forEach {
                    loop(it, uiAction)
                }
            }
        }
    }
}

@Composable
private fun loop(components: Components, uiAction: (UiEvents) -> Unit) {
    when (components) {
        is Components.ComponentsText -> ComponentsText(components)
        is Components.ComponentContainer -> ComponentsContainer(components, uiAction)
        is Components.ComponentsButton -> ComponentsButton(components, uiAction)
        is Components.ComponentsInput -> ComponentsInput(components, uiAction)
    }
}

@Composable
fun ComponentsContainer(
    componentsContainer: Components.ComponentContainer,
    uiAction: (UiEvents) -> Unit) {
    loop(components = componentsContainer, uiAction = uiAction)
}

@Composable
fun ComponentsText(textComponents: Components.ComponentsText) {
    Text(text = textComponents.text)
}

@Composable
fun ComponentsInput(
    inputComponent: Components.ComponentsInput,
    uiAction: (UiEvents) -> Unit
) {
    TextField(value = "", onValueChange = {
        uiAction(UiEvents.InputFieldUpdated(it))
    },
        placeholder = { Text(text = inputComponent.hint) })
}

@Composable
fun ComponentsButton(
    buttonComponents: Components.ComponentsButton,
    uiAction: (UiEvents) -> Unit
) {
    OutlinedButton(onClick = { uiAction(UiEvents.SubmitClicked) }) {
        Text(text = buttonComponents.text)
    }
}


@Composable
fun CircularProfileImageList(
    subjects: List<Subject>,
    uiAction: (UiEvents) -> Unit,
    onNavigateToLessonScreen: (String) -> Unit,
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        columns = GridCells.Adaptive(minSize = 64.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(subjects) { subject ->
            CircularItem(
                googleIcon = Icons.Default.Add,
                text = subject.title,
                onClick = {
                    uiAction(UiEvents.SubjectClicked(subject))
                    onNavigateToLessonScreen.invoke(subject.title)
                }
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenContentPreview(
    @PreviewParameter(HomeScreenContentPreviewParameter::class) uiState: HomeContract.UiState
) {
    HomeScreenContent(
        uiState = uiState,
        uiAction = {},
        onNavigateToLessonScreen = {},
    )
}
