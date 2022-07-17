package com.example.restapicallwithcaching.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateTimeFormatter {
    fun getFormattedDateInTimeStamp(dateString: String, formate: String): Long? {
        return try {
            val dateFormat: DateFormat = SimpleDateFormat(formate)
            val date: Date? = dateFormat.parse(dateString)
            date?.time
        } catch (e: Exception) {
            0
        }
    }
}