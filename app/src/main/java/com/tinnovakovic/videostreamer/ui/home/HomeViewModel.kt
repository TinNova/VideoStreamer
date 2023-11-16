package com.tinnovakovic.videostreamer.ui.home

import android.util.Log
import com.tinnovakovic.videostreamer.data.StreamerRepo
import com.tinnovakovic.videostreamer.data.models.Component
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val streamerRepo: StreamerRepo) :
    HomeContract.ViewModel() {

    override val _uiState: MutableStateFlow<HomeContract.UiState> =
        MutableStateFlow(initialUiState())

    init {
        updateUiState {
            it.copy(components =
                listOf(
                    Component.Container(type = "Container", component =
                        listOf(
                            Component.Container(type = "Container", component =
                                listOf(
                                    Component.Text(type = "Text", text = "Pay with Ducks"),
                                    Component.Text(type = "Text", text = "1. Go to your Ducks app to get your Duck code."),
                                    Component.Text(type = "Text", text = "2. Paste your Duck code here."),
                                    Component.Input(id = "duck-code", type = "Input", hint = "Enter Duck Code", value = ""),
                                    Component.Input(id = "cat-code", type = "Input", hint = "Enter Cat Code", value = ""),
                                    Component.Input(id = "monkey-code", type = "Input", hint = "Enter Monkey Code", value = ""),
                                    )
                            ),
                            Component.Button(type = "Button", text = "Submit", onClick = "SUBMIT")
                        )
                    )
                )
            )
        }
    }

    override fun onUiEvent(event: HomeContract.UiEvents) {
        when (event) {
            is HomeContract.UiEvents.SubjectClicked -> {
                Log.w(
                    "HomeViewModel: ",
                    "Subject Clicked: ${event.subject.title} Id = ${event.subject.id}"
                )
            }

            is HomeContract.UiEvents.InputFieldUpdated -> updateInputFieldWithNewValue(
                event.inputComponent,
                event.newValue
            )

            is HomeContract.UiEvents.ButtonClicked -> onButtonClicked(event.inputValue)
        }
    }

    private fun updateInputFieldWithNewValue(
        inputComponent: Component.Input,
        newValue: String
    ) {

        val updatedInputComponent = Component.Input(
            id = inputComponent.id,
            hint = inputComponent.hint,
            type = inputComponent.type,
            value = newValue
        )

        val mutableComponents: MutableList<Component> = uiState.value.components.toMutableList()

        // Update the value of the Input component with a new value
        val updatedComponents: List<Component> = updateInputValue(uiState.value.components, newValue = newValue)


        // Print the updated list of components
        println("TIN $updatedComponents")

        updateUiState {
            it.copy(components = updatedComponents)
        }
    }

    private fun updateInputValue(components: List<Component>, newValue: String): List<Component> {
        return components.map { component ->
            when (component) {
                is Component.Container -> {
                    val updatedComponents = updateInputValue(component.component, newValue)
                    component.copy(component = updatedComponents)
                }
                is Component.Input -> {
                    // Update the value of the Input component
                    component.copy(value = newValue)
                }
                else -> component
            }
        }
    }

    private fun onButtonClicked(inputValue: MutableList<String>) {
//        val inputComponent: Component.Input = uiState.value.components.first {
//            it is Component.Input
//        } as Component.Input

        println("TIN BUTTON CLICKED: ${inputValue[0]}")

    }

    private companion object {
        fun initialUiState() = HomeContract.UiState(
            components = emptyList()
        )
    }
}
