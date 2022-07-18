package com.example.restapicallwithcaching

import com.example.restapicallwithcaching.utils.DateTimeFormatter
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DateTimeFormatterTest {
    @Test
    @Throws(Exception::class)
    fun isDateFormat() {
        assertEquals(DateTimeFormatter.getFormattedDateInTimeStamp("18/07/2022", "dd/MM/yyyy"), 1658080800000)
        assertEquals(DateTimeFormatter.getFormattedDate("18/07/2022", "dd/MM/yyyy", "MM-dd-yy"), "07-18-22")
    }
}