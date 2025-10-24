package ru.simbirdevs.task_parthnerkin.utils

object Dates {
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

    fun monthEnglish(number: String): String {
        return when (number) {
            "01" -> "JUN"
            "02" -> "FEB"
            "03" -> "MAR"
            "04" -> "APR"
            "05" -> "MAY"
            "06" -> "JUN"
            "07" -> "JUL"
            "08" -> "AUG"
            "09" -> "SEP"
            "10" -> "OCT"
            "11" -> "NOV"
            "12" -> "DEC"
            else -> ""
        }
    }
}