package com.tinnovakovic.videostreamer.ui.home

import com.tinnovakovic.videostreamer.base.BaseViewModel
import com.tinnovakovic.videostreamer.base.BaseUiEvent
import com.tinnovakovic.videostreamer.base.BaseUiState
import com.tinnovakovic.videostreamer.data.models.Component
import com.tinnovakovic.videostreamer.data.models.Subject
import javax.annotation.concurrent.Immutable

interface HomeContract {

    abstract class ViewModel: BaseViewModel<UiEvents, UiState>()

    @Immutable
    data class UiState(
        val components: List<Component>,
        ) : BaseUiState {}

    sealed class UiEvents: BaseUiEvent {
        data class SubjectClicked(val subject: Subject) : UiEvents()
        data class InputFieldUpdated(val inputComponent: Component.Input, val newValue: String): UiEvents()
        data class ButtonClicked(val inputValue: MutableList<String>): UiEvents()
    }
}