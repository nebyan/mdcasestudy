package com.casestudy.util

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getDate(date: String): String{
        val calendar = Calendar.getInstance()
        val time = date.toLong() * 1000
        calendar.timeInMillis = time
        val dateFormat = SimpleDateFormat("dd/MM/yyyy, EEE")
        return dateFormat.format(calendar.time)
    }

}