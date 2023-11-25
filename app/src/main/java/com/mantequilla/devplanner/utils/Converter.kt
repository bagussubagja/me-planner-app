package com.mantequilla.devplanner.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.time.LocalTime

class Converter {
    companion object {
        fun getCurrentDate(): String {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            return LocalDate.now().format(formatter)
        }
        fun getCurrentDateFormatted(): String {
            val formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", Locale.ENGLISH)
            return LocalDate.now().format(formatter)
        }
        fun formatTime(localTime: LocalTime): String {
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
            return localTime.format(formatter)
        }
    }
}