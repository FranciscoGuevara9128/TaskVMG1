package com.uam.taskvmg1.repository

import com.uam.taskvmg1.model.TaskItem
import retrofit2.http.GET
import retrofit2.Response

interface TaskApiService {
    @GET("/api/task/all")
    suspend fun getTask():
            Response<List<TaskItem>>
}