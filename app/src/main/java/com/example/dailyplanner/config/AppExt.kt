package com.example.dailyplanner.config

import com.example.dailyplanner.config.Constants.ONE_DAY
import java.text.SimpleDateFormat
import java.util.*

object AppExt {
    private fun Calendar.toTimestamp(): Long {
        return this.time.time
    }

    fun Calendar.getDayPeriod(): Pair<Long, Long> {
        val dayStart = this.toTimestamp()
        return dayStart to dayStart + ONE_DAY
    }

    fun Pair<Long?, Long?>.toHour(): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return "${dateFormat.format(this.first)}"
    }

    fun Pair<Long?, Long?>.toHours(): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return "${dateFormat.format(this.first)} - ${dateFormat.format(this.second)}"
    }


    fun Long?.toPeriod(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy - HH:mm", Locale.getDefault())
        return dateFormat.format(this)
    }
}