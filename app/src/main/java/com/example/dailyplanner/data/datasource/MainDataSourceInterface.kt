package com.example.dailyplanner.data.datasource

import com.example.dailyplanner.data.model.GetTasksResponse

interface MainDataSourceInterface {
    suspend fun getTasks(): List<GetTasksResponse>
}