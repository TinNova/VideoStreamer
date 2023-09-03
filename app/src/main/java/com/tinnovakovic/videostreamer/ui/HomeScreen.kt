package com.tinnovakovic.videostreamer.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tinnovakovic.videostreamer.ui.HomeContract.UiState

@Composable
fun HomeScreen(viewModel: HomeContract.ViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState,
        uiAction = viewModel::onUiEvent
    )

    BackHandler {
        viewModel.onUiEvent(HomeContract.UiEvents.BackPressed)
    }

}

@Composable
private fun HomeScreenContent(uiState: UiState, uiAction: (HomeContract.UiEvents) -> Unit) {

    Column(modifier = Modifier.padding(16.dp)) {
        Text(uiState.title)
        Button(
            onClick = { uiAction(HomeContract.UiEvents.ButtonClicked) },
        ) {
            Text("Click Me")
        }
    }
}