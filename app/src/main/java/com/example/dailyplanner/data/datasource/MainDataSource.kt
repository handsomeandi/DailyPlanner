package com.example.dailyplanner.data.datasource

import com.example.dailyplanner.data.MainApi
import com.example.dailyplanner.data.model.GetTasksResponse
import javax.inject.Inject

class MainDataSource @Inject constructor(
    private val api: MainApi
) : MainDataSourceInterface {

    override suspend fun getTasks(): List<GetTasksResponse> {
        val response = api.getTasks()

        if (response.isSuccessful) {
            response.body()?.let {
                return it
            } ?: run {
                throw Exception("Empty Body")
            }
        } else {
            throw Exception(response.message())
        }
    }


}