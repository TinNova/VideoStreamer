package com.tinnovakovic.videostreamer.ui.lesson

import com.tinnovakovic.videostreamer.base.BaseUiEvent
import com.tinnovakovic.videostreamer.base.BaseUiState
import com.tinnovakovic.videostreamer.base.BaseViewModel
import com.tinnovakovic.videostreamer.data.models.Lesson
import com.tinnovakovic.videostreamer.data.models.Primer
import javax.annotation.concurrent.Immutable

interface LessonContract {

    abstract class ViewModel : BaseViewModel<UiEvents, UiState>()

    @Immutable
    data class UiState(
        val subjectTitle: String,
        val lessons: List<Lesson>,
        val cachedCurrentVideo: String?, //Should be a CachedVideo object
        val upNavigateClicked: Boolean,
        val primer: Primer,
        val currentTime: Int,
        val isStartEnabled: Boolean,
    ) : BaseUiState {}

    sealed class UiEvents : BaseUiEvent {
        object UpClicked : UiEvents()
        data class ComponentButtonClicked(val clickId: String) : UiEvents()
        data class StartClicked(val currentTime: Int): UiEvents()
        object PauseClicked: UiEvents()
        object StopClicked: UiEvents()


    }
}
