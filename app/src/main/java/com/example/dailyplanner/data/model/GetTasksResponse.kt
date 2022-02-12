package com.example.dailyplanner.data.model

import com.google.gson.annotations.SerializedName

data class GetTasksResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("date_start") val dateStart: String? = null,
    @SerializedName("date_finish") val dateEnd: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null
)

