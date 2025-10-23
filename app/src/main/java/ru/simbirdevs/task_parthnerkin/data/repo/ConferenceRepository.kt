package ru.simbirdevs.task_parthnerkin.data.repo

import kotlinx.coroutines.delay
import ru.simbirdevs.task_parthnerkin.data.Category
import ru.simbirdevs.task_parthnerkin.data.ConferenceData
import ru.simbirdevs.task_parthnerkin.data.ImageData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConferenceRepository @Inject constructor() {

    private val conferenceMockList = (0..10).map {
        ConferenceData(
            id = "$it",
            conferenceName = "Conference $it",
            conferenceFormat = "Format $it",
            conferenceStatusTitle = "Status $it",
            image = listOf(ImageData("https://picsum.photos/200/300?random=$it")),
            startDate = "2025-0${(it % 12) + 1}-${10 + it}",
            endDate = "2025-0${(it % 12) + 1}-${11 + it}",
            country = "Country $it",
            city = "City $it",
            category = listOf(Category("Category ${it % 5}")),
        )
    }

    suspend fun getAllConferences(): List<ConferenceData> {
        delay(1000)
        return conferenceMockList
    }
}