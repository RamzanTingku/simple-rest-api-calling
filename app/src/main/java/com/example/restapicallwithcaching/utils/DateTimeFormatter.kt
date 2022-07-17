package com.example.restapicallwithcaching.utils

import java.text.DateFormat
import java.text.ParseException
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

    fun getFormattedDate(
        originalDate: String?,
        originFormat: String?,
        targetFormat: String?,
    ): String? {
        var date = Date()
        val original =
            SimpleDateFormat(originFormat, Locale.ENGLISH)
        val target = SimpleDateFormat(targetFormat)
        try {
            date = originalDate?.let { original.parse(it) } as Date
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return target.format(date)
    }
}