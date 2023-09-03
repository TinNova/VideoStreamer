package com.tinnovakovic.videostreamer.ui

import com.tinnovakovic.videostreamer.base.BaseViewModel
import com.tinnovakovic.videostreamer.base.BaseUiEvent
import com.tinnovakovic.videostreamer.base.BaseUiState
import javax.annotation.concurrent.Immutable

interface HomeContract {

    abstract class ViewModel: BaseViewModel<UiEvents, UiState>()

    @Immutable
    data class UiState(
        val title: String

    ) : BaseUiState {}

    sealed class UiEvents: BaseUiEvent {
        object BackPressed : UiEvents()
        object ButtonClicked : UiEvents()
    }
}