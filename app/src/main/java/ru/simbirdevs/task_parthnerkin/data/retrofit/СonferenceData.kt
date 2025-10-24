package ru.simbirdevs.task_parthnerkin.data.retrofit


data class Root(
    val error: Any?,
    val data: Data
)

data class Data(
    val counts: Int,
    val result: List<Result>
)

data class Result(
    val view_type: Int,
    val conference: ConferenceData
)
data class ConferenceData(
    val id: String = "",
    val name: String = "",
    val format: String = "",
    val status_title: String = "",
    val image: ImageData,
    val start_date: String = "",
    val end_date: String = "",
    val country: String = "",
    val city: String = "",
    val categories: List<Category>,
)

data class Category(
    val name: String = "",
)

data class ImageData(
    val url: String = "",
)