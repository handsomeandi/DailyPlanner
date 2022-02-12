package com.example.dailyplanner.data.repository

import com.example.dailyplanner.data.base.Result
import com.example.dailyplanner.data.datasource.MainDataSourceInterface
import com.example.dailyplanner.domain.Mapper.mapToDomain
import com.example.dailyplanner.domain.model.Task
import com.example.dailyplanner.domain.repository.MainRepositoryInterface
import javax.inject.Inject

class MainRepository @Inject constructor(private val dataSource: MainDataSourceInterface) :
    MainRepositoryInterface {

    override suspend fun getTasks(): Result<List<Task>> {
        return try {
            val data = dataSource.getTasks()
            Result.Success(data.mapToDomain())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}