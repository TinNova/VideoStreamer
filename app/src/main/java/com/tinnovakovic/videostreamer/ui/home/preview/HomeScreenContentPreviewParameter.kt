package com.tinnovakovic.videostreamer.ui.home.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.tinnovakovic.videostreamer.data.models.Component
import com.tinnovakovic.videostreamer.data.models.Subject
import com.tinnovakovic.videostreamer.ui.home.HomeContract

class HomeScreenContentPreviewParameter : PreviewParameterProvider<HomeContract.UiState> {
    override val values = sequenceOf(
        HomeContract.UiState(
            components = getComponents()
        ),
    )

    private fun getComponents(): List<Component> {
        return listOf<Component>(
            Component.Container(
                type = "Container", component = listOf<Component>(
                    Component.Container(
                        type = "Container", component = listOf<Component>(
                            Component.Text(type = "Text", text = "Pay with Ducks"),
                            Component.Text(type = "Text", text = "1. Go to your Ducks app to get your Duck code."),
                            Component.Text(type = "Text", text = "2. Paste your Duck code here."),
                            Component.Input(id= "duck-code", type = "Input", hint = "Enter Duck Code", value = ""),
                            )
                    ),
                    Component.Button(type = "Button", text = "Submit", onClick = "SUBMIT")
                )
            )
        )
    }

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
