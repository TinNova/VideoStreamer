package com.tinnovakovic.videostreamer.ui.lesson

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.tinnovakovic.videostreamer.NavDirections
import com.tinnovakovic.videostreamer.NavManager
import com.tinnovakovic.videostreamer.data.local_api.StreamerLocalJson
import com.tinnovakovic.videostreamer.data.models.Event
import com.tinnovakovic.videostreamer.data.models.Primer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(
    private val getLessonsUseCase: GetLessonsUseCase,
    private val streamerLocalJson: StreamerLocalJson,
    private val navManager: NavManager,
    savedStateHandle: SavedStateHandle,
) : LessonContract.ViewModel() {

    private val subjectTitle: String = checkNotNull(savedStateHandle["subjectTitle"])
    override val _uiState: MutableStateFlow<LessonContract.UiState> =
        MutableStateFlow(initialUiState())

    init {
        val lessons = getLessonsUseCase.execute(subjectTitle)
        val primer = streamerLocalJson.getPrimer()

        updateUiState {
            it.copy(
                subjectTitle = subjectTitle,
                lessons = lessons,
                primer = primer
            )
        }
    }

    override fun onUiEvent(event: LessonContract.UiEvents) {
        when (event) {
            is LessonContract.UiEvents.UpClicked -> {
                navManager.navigate(NavDirections.home)
                Log.w("LessonViewModel: ", "Up Button Clicked")
            }

            is LessonContract.UiEvents.ComponentButtonClicked -> {
                val eventToUpload: Event = uiState.value.primer.events.first { primerEvent ->
                    primerEvent.type == event.clickId
                }

                Log.d(eventToUpload.type, eventToUpload.actions.first().data.value)
            }

            is LessonContract.UiEvents.StartClicked -> startTimer(event.currentTime)
            LessonContract.UiEvents.PauseClicked -> pauseTimer()
            LessonContract.UiEvents.StopClicked -> stop()
        }
    }

    private var job: Job? = null

    private fun startTimer(currentTime: Int) {
        job = viewModelScope.launch {
            this.launch {
                var second = currentTime

                while (second != 100) {
                    delay(1000)
                    second++
                    updateUiState { it.copy(currentTime = second, isStartEnabled = false) }
                }
            }
        }

    }

    private fun pauseTimer() {
        job?.let {
            it.cancel()
            job = null
        }
        updateUiState { it.copy(isStartEnabled = true) }
    }

    private fun stop() {
        job?.let {
            it.cancel()
            job = null
        }

        updateUiState { it.copy(currentTime = 0, isStartEnabled = true) }
    }

    private companion object {
        fun initialUiState() = LessonContract.UiState(
            subjectTitle = "",
            lessons = emptyList(),
            cachedCurrentVideo = null,
            upNavigateClicked = false,
            primer = Primer(emptyList(), emptyList()),
            currentTime = 0,
            isStartEnabled = true
        )
    }
}
