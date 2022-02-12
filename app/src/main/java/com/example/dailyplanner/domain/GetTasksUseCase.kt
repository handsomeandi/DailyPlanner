package com.example.dailyplanner.domain

import com.example.dailyplanner.domain.repository.MainRepositoryInterface
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val repository: MainRepositoryInterface) {
    suspend fun getTasks() = repository.getTasks()
}