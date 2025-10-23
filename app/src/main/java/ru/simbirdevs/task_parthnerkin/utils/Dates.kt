package ru.simbirdevs.task_parthnerkin.utils

object Dates {
    fun dateValidation(date: String):String {
        val dateStr = date.split("-")
        val formattedString = "${month(dateStr[1])}, ${dateStr[2]}"
        return formattedString
    }

    fun month(number: String): String {
        return when (number) {
            "01" -> "Январь"
            "02" -> "Февраль"
            "03" -> "Март"
            "04" -> "Апрель"
            "05" -> "Май"
            "06" -> "Июнь"
            "07" -> "Июль"
            "08" -> "Август"
            "09" -> "Сентябрь"
            "10" -> "Октябрь"
            "11" -> "Ноябрь"
            "12" -> "Декабрь"
            else -> ""
        }
    }
}