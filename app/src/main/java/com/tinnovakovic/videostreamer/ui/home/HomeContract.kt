package com.tinnovakovic.videostreamer.ui.home

import com.tinnovakovic.videostreamer.base.BaseViewModel
import com.tinnovakovic.videostreamer.base.BaseUiEvent
import com.tinnovakovic.videostreamer.base.BaseUiState
import com.tinnovakovic.videostreamer.data.models.Subject
import javax.annotation.concurrent.Immutable

interface HomeContract {

    abstract class ViewModel: BaseViewModel<UiEvents, UiState>()

    @Immutable
    data class UiState(
        val subjects: List<Subject>,
        val cachedCurrentVideo: String?, //CachedVideo object
        ) : BaseUiState {}

    sealed class UiEvents: BaseUiEvent {
        data class SubjectClicked(val subject: Subject) : UiEvents()
    }
}