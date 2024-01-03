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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tinnovakovic.videostreamer.composables.UiElement.CircularItem
import com.tinnovakovic.videostreamer.data.models.Subject
import com.tinnovakovic.videostreamer.ui.home.HomeContract.*
import com.tinnovakovic.videostreamer.ui.home.preview.HomeScreenContentPreviewParameter

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState,
        uiAction = viewModel::onUiEvent,
    )
}

@Composable
fun HomeScreenContent(
    uiState: UiState,
    uiAction: (UiEvents) -> Unit,
) {

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            CircularProfileImageList(uiState.subjects, uiAction)
        }
    }
}

@Composable
fun CircularProfileImageList(
    subjects: List<Subject>,
    uiAction: (UiEvents) -> Unit,
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
    )
}
