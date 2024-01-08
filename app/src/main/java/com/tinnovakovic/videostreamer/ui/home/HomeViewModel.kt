package com.tinnovakovic.videostreamer.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.tinnovakovic.videostreamer.NavDirections
import com.tinnovakovic.videostreamer.NavManager
import com.tinnovakovic.videostreamer.data.StreamerRepo
import com.tinnovakovic.videostreamer.data.models.CatFact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val streamerRepo: StreamerRepo,
    private val navManager: NavManager
) : HomeContract.ViewModel() {

    override val _uiState: MutableStateFlow<HomeContract.UiState> =
        MutableStateFlow(initialUiState())

    init {
        val subjects = streamerRepo.getSubjects()

        viewModelScope.launch {
            val catFacts = streamerRepo.getCatFacts()

            updateUiState { it.copy(subjects = subjects, catFacts = catFacts) }
        }
    }

    override fun onUiEvent(event: HomeContract.UiEvents) {
        when (event) {
            is HomeContract.UiEvents.SubjectClicked -> {
                navManager.navigate(directions = NavDirections.lesson(event.subject.title))
                Log.w(
                    "HomeViewModel: ",
                    "Subject Clicked: ${event.subject.title} Id = ${event.subject.id}"
                )
            }

            is HomeContract.UiEvents.CatFactClicked -> {

                val newCatFacts: List<CatFact> = uiState.value.catFacts.map { catFact ->
                    if (catFact == event.catFact) {
                        event.catFact.copy(isSelected = !event.catFact.isSelected)
                    } else {
                        catFact
                    }
                }

                updateUiState { it.copy(catFacts = newCatFacts) }
            }
        }
    }


    private companion object {
        fun initialUiState() = HomeContract.UiState(
            subjects = emptyList(),
            catFacts = emptyList()
        )
    }
}