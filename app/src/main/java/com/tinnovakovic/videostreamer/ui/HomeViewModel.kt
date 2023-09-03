package com.tinnovakovic.videostreamer.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : HomeContract.ViewModel() {

    override val _uiState: MutableStateFlow<HomeContract.UiState> = MutableStateFlow(initialUiState())

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