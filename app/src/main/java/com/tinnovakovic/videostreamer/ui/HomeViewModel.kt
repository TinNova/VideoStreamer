package com.tinnovakovic.videostreamer.ui

import com.tinnovakovic.videostreamer.data.StreamerRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val streamerRepo: StreamerRepo) : HomeContract.ViewModel() {

    override val _uiState: MutableStateFlow<HomeContract.UiState> = MutableStateFlow(initialUiState())

    init {
        val subjects = streamerRepo.getSubjects()
        updateUiState { it.copy(title = subjects[0].title) }
    }

    override fun onUiEvent(event: HomeContract.UiEvents) {
        when (event) {
            HomeContract.UiEvents.ButtonClicked -> {
                //do something
            }
            HomeContract.UiEvents.BackPressed -> {}
        }
    }


    private companion object {
        fun initialUiState() = HomeContract.UiState(
            title = "hello world"
        )
    }
}