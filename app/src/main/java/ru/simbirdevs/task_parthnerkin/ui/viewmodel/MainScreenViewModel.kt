package ru.simbirdevs.task_parthnerkin.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.simbirdevs.task_parthnerkin.data.Category
import ru.simbirdevs.task_parthnerkin.data.ConferenceData
import ru.simbirdevs.task_parthnerkin.data.ImageData
import ru.simbirdevs.task_parthnerkin.ui.UiState

class MainScreenViewModel() : ViewModel() {
    private val _conferences = MutableStateFlow<UiState>(UiState(emptyList()))
    val conferences: StateFlow<UiState> = _conferences.asStateFlow()

    init {
        loadConferences()
    }

    private fun loadConferences() {
        viewModelScope.launch(Dispatchers.IO) {
            _conferences.value = UiState(conferenceList = getConferenceList())
        }
    }

    private fun getConferenceList(): List<ConferenceData> {
        return (0..10).map {
            ConferenceData(
                id = "$it",
                conferenceName = "Name $it",
                conferenceFormat = "Format $it",
                conferenceStatusTitle = "Status title $it",
                image = listOf(ImageData("https://cdn.cloudflare.steamstatic.com/steamcommunity/public/images/avatars/33/33acc36c9e8bcb552429e05b1ecd721bcc321dec_full.jpg")),
                startDate = "2025-03-$it",
                endDate = "2025-03-$it",
                country = "Country $it",
                city = "City $it",
                category = listOf(Category("Category $it")),
            )
        }
    }
}