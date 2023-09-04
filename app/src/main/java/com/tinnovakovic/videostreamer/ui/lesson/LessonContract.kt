package com.tinnovakovic.videostreamer.ui.lesson

import androidx.navigation.NavController
import com.tinnovakovic.videostreamer.base.BaseUiEvent
import com.tinnovakovic.videostreamer.base.BaseUiState
import com.tinnovakovic.videostreamer.base.BaseViewModel
import com.tinnovakovic.videostreamer.data.models.Lesson
import javax.annotation.concurrent.Immutable

interface LessonContract {

    abstract class ViewModel: BaseViewModel<UiEvents, UiState>()

    @Immutable
    data class UiState(
        val subjectTitle: String,
        val lessons: List<Lesson>,
        val cachedCurrentVideo: String? //Should be a CachedVideo object
    ) : BaseUiState {}

    sealed class UiEvents: BaseUiEvent {
        data class UpClicked(val navController: NavController) : UiEvents()

    }
}
