package ru.simbirdevs.task_parthnerkin.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.simbirdevs.task_parthnerkin.data.ConferenceData
import ru.simbirdevs.task_parthnerkin.data.repo.ConferenceRepository
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val conferenceRepository: ConferenceRepository
) : ViewModel() {
    private val _conferences = MutableStateFlow<List<ConferenceData>>(emptyList())
    val conferences: StateFlow<List<ConferenceData>> = _conferences.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadConferences()
    }

    fun loadConferences() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = null

                _conferences.value = conferenceRepository.getAllConferences()

            } catch (e: Exception) {
                _errorMessage.value = "Failed to load conferences: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}