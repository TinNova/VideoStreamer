package com.tinnovakovic.videostreamer.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tinnovakovic.videostreamer.data.models.Component
import com.tinnovakovic.videostreamer.ui.home.HomeContract.*
import com.tinnovakovic.videostreamer.ui.home.preview.HomeScreenContentPreviewParameter

@Composable
fun HomeScreen(viewModel: ViewModel, onNavigateToLessonScreen: (String) -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreenContent(
        uiState = uiState,
        uiAction = viewModel::onUiEvent,
    )
}

@Composable
private fun HomeScreenContent(
    uiState: UiState,
    uiAction: (UiEvents) -> Unit,
) {

    val value: MutableState<MutableList<String>> = remember {
        mutableStateOf(mutableListOf<String>())
    }

    value.value.add(0, "")

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {

            uiState.components.forEach { component ->
                Loop(component, uiAction, value)
            }

        }
    }
}

@Composable
private fun Loop(component: Component, uiAction: (UiEvents) -> Unit, value: MutableState<MutableList<String>>) {
    when (component) {
        is Component.Container -> {
            component.component.forEach {
                Loop(it, uiAction, value)
            }
        }
        is Component.Button -> ButtonComponent(component = component, uiAction, value)
        is Component.Input -> InputComponent(component = component, uiAction, value)
        is Component.Text -> TextComponent(component = component)
    }
}

@Composable
fun TextComponent(component: Component.Text) {
    Text(text = component.text)
}

@Composable
fun InputComponent(
    component: Component.Input,
    uiAction: (UiEvents) -> Unit,
    value: MutableState<MutableList<String>>
) {

    TextField(value = value.value[0],
        onValueChange = {
            value.value[0] = it
        }, placeholder = { Text(text = component.hint) })


//        uiAction(
//            UiEvents.InputFieldUpdated(
//                inputComponent = component,
//                newValue = value = it
//            )
//        )

}

@Composable
fun ButtonComponent(
    component: Component.Button,
    uiAction: (UiEvents) -> Unit,
    value: MutableState<MutableList<String>>
) {
    OutlinedButton(onClick = { uiAction(UiEvents.ButtonClicked(inputValue = value.value)) }) {
        Text(text = component.text)
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
