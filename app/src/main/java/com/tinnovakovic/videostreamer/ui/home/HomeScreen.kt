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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.tinnovakovic.videostreamer.composables.UiElement.CircularItem
import com.tinnovakovic.videostreamer.data.models.Subject
import com.tinnovakovic.videostreamer.ui.home.HomeContract.*

@Composable
fun HomeScreen(viewModel: ViewModel, navController: NavHostController) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState,
        uiAction = viewModel::onUiEvent,
        navController = navController
    )
}

@Composable
private fun HomeScreenContent(
    uiState: UiState,
    uiAction: (UiEvents) -> Unit,
    navController: NavHostController
) {

    Column {
        CircularProfileImageList(uiState.subjects, uiAction, navController)
    }

}

@Composable
fun CircularProfileImageList(
    subjects: List<Subject>,
    uiAction: (UiEvents) -> Unit,
    navController: NavHostController
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
                onClick = { uiAction(UiEvents.SubjectClicked(subject, navController)) }
            )
        }
    }
}