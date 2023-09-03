package com.tinnovakovic.videostreamer.ui

import android.util.Log
import com.tinnovakovic.videostreamer.data.StreamerRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(private val streamerRepo: StreamerRepo) : LessonContract.ViewModel() {

    override val _uiState: MutableStateFlow<LessonContract.UiState> = MutableStateFlow(initialUiState())

    init {
        //TODO: Pass Subject from Home Screen to Lesson Screen
        // Then with the Subject you can display the title and get Videos
        val subjects = streamerRepo.getSubjects()
        updateUiState { it.copy(subjects = subjects) }
    }

    override fun onUiEvent(event: LessonContract.UiEvents) {
        when (event) {
            is LessonContract.UiEvents.SubjectClicked -> {
                Log.w("Subject Clicked: ", "${event.subject.title} Id = ${event.subject.id}")
            }

            is LessonContract.UiEvents.UpClicked -> {
                event.navController.navigateUp()
            }
        }
    }


    private companion object {
        fun initialUiState() = LessonContract.UiState(
            subjects = emptyList(),
            cachedCurrentVideo = null
        )
    }
}