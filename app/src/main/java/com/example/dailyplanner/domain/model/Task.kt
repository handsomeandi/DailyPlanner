package com.example.dailyplanner.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: Int? = null,
    val dateStart: Long? = null,
    val dateEnd: Long? = null,
    val name: String? = null,
    val description: String? = null
) : Parcelable {
    fun isTaskInsidePeriod(period: Pair<Long, Long>): Boolean {
        if (dateStart != null && dateEnd != null) {
            return dateStart <= period.second && dateEnd >= period.first
        }
        return false
    }

}

