package com.example.dailyplanner.domain.repository

import com.example.dailyplanner.data.base.Result
import com.example.dailyplanner.domain.model.Task

interface MainRepositoryInterface {
    suspend fun getTasks(): Result<List<Task>>
}