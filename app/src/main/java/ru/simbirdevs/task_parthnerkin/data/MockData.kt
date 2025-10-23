package ru.simbirdevs.task_parthnerkin.data

object MockData {

    val conferenceData = ConferenceData(
        id = "78",
        conferenceName = "Name 78",
        conferenceFormat = "Format 78",
        conferenceStatusTitle = "Status title 78",
        image = listOf(ImageData("https://cdn.cloudflare.steamstatic.com/steamcommunity/public/images/avatars/33/33acc36c9e8bcb552429e05b1ecd721bcc321dec_full.jpg")),
        startDate = "78",
        endDate = "78",
        country = "Country 78",
        city = "City 78",
        category = listOf(Category("Category 78")),
    )
}