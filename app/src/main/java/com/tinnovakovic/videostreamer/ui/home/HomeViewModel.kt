package com.tinnovakovic.videostreamer.ui.home

import android.util.Log
import androidx.annotation.VisibleForTesting
import com.tinnovakovic.videostreamer.data.StreamerRepo
import com.tinnovakovic.videostreamer.data.StreamerRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val streamerRepoImpl: StreamerRepoImpl) :
    HomeContract.ViewModel() {

    override val _uiState: MutableStateFlow<HomeContract.UiState> =
        MutableStateFlow(initialUiState())

    init {
        val subjects = streamerRepoImpl.getParentComponent()
        updateUiState { it.copy(screenParent = subjects) }
    }

    @VisibleForTesting
    var inputFieldText: String? = null

    override fun onUiEvent(event: HomeContract.UiEvents) {
        when (event) {
            is HomeContract.UiEvents.SubjectClicked -> {
                Log.w(
                    "HomeViewModel: ",
                    "Subject Clicked: ${event.subject.title} Id = ${event.subject.id}"
                )
            }

            is HomeContract.UiEvents.InputFieldUpdated -> {
                inputFieldText = event.input
            }

            HomeContract.UiEvents.SubmitClicked -> {
                onSubmitClicked()
            }
        }
    }

    private fun onSubmitClicked() {

        if (!this.inputFieldText.isNullOrEmpty()) {
            Log.d("HomeViewModel", "$inputFieldText")
        }


    }


    private companion object {
        fun initialUiState() = HomeContract.UiState(
            subjects = emptyList(),
            cachedCurrentVideo = null,
            screenParent = null
        )
    }
}