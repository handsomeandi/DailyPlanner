package com.example.dailyplanner.data

import com.example.dailyplanner.data.model.GetTasksResponse
import retrofit2.Response
import retrofit2.http.GET

interface MainApi {
    @GET("get_tasks")
    suspend fun getTasks(): Response<List<GetTasksResponse>>
}