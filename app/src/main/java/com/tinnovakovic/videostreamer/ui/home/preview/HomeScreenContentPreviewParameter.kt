package com.tinnovakovic.videostreamer.ui.home.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.tinnovakovic.videostreamer.data.models.Subject
import com.tinnovakovic.videostreamer.ui.home.HomeContract

class HomeScreenContentPreviewParameter : PreviewParameterProvider<HomeContract.UiState> {
    override val values = sequenceOf(
        HomeContract.UiState(
            subjects = getSubjects(),
            catFacts = emptyList()
        ),
    )

    private fun getSubjects(): List<Subject> {
        return listOf<Subject>(
            Subject(id = 0, title = "English"),
            Subject(id = 0, title = "Government"),
            Subject(id = 0, title = "Mathematics"),
            Subject(id = 0, title = "English"),
            Subject(id = 0, title = "Government"),
            Subject(id = 0, title = "Mathematics"),
            Subject(id = 0, title = "English"),
            Subject(id = 0, title = "Government"),
            Subject(id = 0, title = "Mathematics"),
            Subject(id = 0, title = "English"),
            Subject(id = 0, title = "Government"),
            Subject(id = 0, title = "Mathematics"),
        )
    }
}
