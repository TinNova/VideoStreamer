package com.tinnovakovic.videostreamer.ui.home

import android.util.Log
import com.tinnovakovic.videostreamer.data.StreamerRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val streamerRepo: StreamerRepo
) : HomeContract.ViewModel() {

    override val _uiState: MutableStateFlow<HomeContract.UiState> =
        MutableStateFlow(initialUiState())

    init {
        val subjects = streamerRepo.getSubjects()
        updateUiState { it.copy(subjects = subjects) }
    }

    override fun onUiEvent(event: HomeContract.UiEvents) {
        when (event) {
            is HomeContract.UiEvents.SubjectClicked -> {
                Log.w(
                    "HomeViewModel: ",
                    "Subject Clicked: ${event.subject.title} Id = ${event.subject.id}"
                )
            }
        }
    }


    private companion object {
        fun initialUiState() = HomeContract.UiState(
            subjects = emptyList(),
            cachedCurrentVideo = null,
        )
    }
}