package com.tinnovakovic.videostreamer.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tinnovakovic.videostreamer.composables.UiElement.CircularItem
import com.tinnovakovic.videostreamer.data.models.CatFact
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .padding(paddingValues),
        ) {
            CircularProfileImageList(uiState.subjects, uiAction)
            ListOfCatFacts(uiState.catFacts, uiAction)
        }
    }
}


fun LazyGridScope.CircularProfileImageList(
    subjects: List<Subject>,
    uiAction: (UiEvents) -> Unit,
) {

    items(count = subjects.size) { i ->
        CircularItem(
            Modifier.padding(vertical = 16.dp),
            googleIcon = Icons.Default.Add,
            text = subjects[i].title,
            onClick = {
                uiAction(UiEvents.SubjectClicked(subjects[i]))
            }
        )
    }
}

fun LazyGridScope.ListOfCatFacts(
    catFacts: List<CatFact>,
    uiAction: (UiEvents) -> Unit
) {
    items(span = { GridItemSpan(4) }, count = catFacts.size) { i ->

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .clickable {
                    uiAction(UiEvents.CatFactClicked(catFacts[i], i))
                }
        ) {
            Text(text = catFacts[i].fact, Modifier.weight(0.8f))

            if (catFacts[i].isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier
                        .size(32.dp)
                        .weight(0.2f)
                )
            } else {
                Spacer(
                    Modifier
                        .width(64.dp)
                        .weight(0.2f)
                )
            }
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
