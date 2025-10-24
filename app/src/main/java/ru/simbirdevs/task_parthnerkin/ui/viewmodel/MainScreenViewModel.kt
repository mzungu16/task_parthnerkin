package ru.simbirdevs.task_parthnerkin.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.simbirdevs.task_parthnerkin.data.retrofit.Result
import ru.simbirdevs.task_parthnerkin.data.repo.ConferenceRepository
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val conferenceRepository: ConferenceRepository
) : ViewModel() {
    private val _conferences = MutableStateFlow<List<Result>>(emptyList())

    private val _groupedList = MutableStateFlow<Map<String, List<Result>>>(emptyMap())
    val groupedList: StateFlow<Map<String, List<Result>>> = _groupedList.asStateFlow()

    init {
        loadConferences()
    }

    private fun loadConferences() {
        viewModelScope.launch(Dispatchers.IO) {
            conferenceRepository.getRemoteConferences().collect { conferences ->
                _conferences.value = conferences
            }
            groupedList()
        }
    }

    private fun groupedList() {
        val grouped = _conferences.value.groupBy { it.conference.start_date.split("-")[1] }
        _groupedList.value = grouped
    }
}