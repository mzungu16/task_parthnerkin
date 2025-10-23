package ru.simbirdevs.task_parthnerkin.data.repo

import ru.simbirdevs.task_parthnerkin.data.Category
import ru.simbirdevs.task_parthnerkin.data.ConferenceData
import ru.simbirdevs.task_parthnerkin.data.ImageData

class ConferenceRepository {
    val conferenceMockList = (0..10).map {
        ConferenceData(
            id = "$it",
            conferenceName = "Name $it",
            conferenceFormat = "Format $it",
            conferenceStatusTitle = "Status title $it",
            image = listOf(ImageData("Image $it")),
            startDate = "$it",
            endDate = "$it",
            country = "Country $it",
            city = "City $it",
            category = listOf(Category("Category $it")),
        )
    }
}