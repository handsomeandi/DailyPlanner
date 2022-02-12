package com.example.dailyplanner.domain

import com.example.dailyplanner.data.model.GetTasksResponse
import com.example.dailyplanner.domain.model.Task

object Mapper {
    fun GetTasksResponse.mapToDomain(): Task {
        return Task(id, dateStart?.toLong(), dateEnd?.toLong(), name, description)
    }

    fun List<GetTasksResponse>.mapToDomain(): List<Task> {
        return this.map {
            it.mapToDomain()
        }
    }
}