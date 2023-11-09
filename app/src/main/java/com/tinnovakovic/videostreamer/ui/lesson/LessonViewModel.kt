package com.tinnovakovic.videostreamer.ui.lesson

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(
    private val getLessonsUseCase: GetLessonsUseCase,
    savedStateHandle: SavedStateHandle,
) : LessonContract.ViewModel() {

    private val subjectTitle: String = checkNotNull(savedStateHandle["subjectTitle"])
    override val _uiState: MutableStateFlow<LessonContract.UiState> =
        MutableStateFlow(initialUiState())

    init {
        val lessons = getLessonsUseCase.execute(subjectTitle)

        updateUiState {
            it.copy(
                subjectTitle = subjectTitle,
                lessons = lessons
            )
        }
    }

    override fun onUiEvent(event: LessonContract.UiEvents) {
        when (event) {
            is LessonContract.UiEvents.UpClicked -> {
                Log.w("LessonViewModel: ", "Up Button Clicked")
            }
        }
    }

    private companion object {
        fun initialUiState() = LessonContract.UiState(
            subjectTitle = "",
            lessons = emptyList(),
            cachedCurrentVideo = null,
            upNavigateClicked = false
        )
    }
}
