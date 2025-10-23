package ru.simbirdevs.task_parthnerkin.data

data class ConferenceData(
    val id: String = "",
    val conferenceName: String = "",
    val conferenceFormat: String = "",
    val conferenceStatusTitle: String = "",
    val image: List<ImageData>,
    val startDate: String = "",
    val endDate: String = "",
    val country: String = "",
    val city: String = "",
    val category: List<Category>,
)

data class Category(
    val categoryName: String = "",
)

data class ImageData(
    val imageUrl: String = "",
)