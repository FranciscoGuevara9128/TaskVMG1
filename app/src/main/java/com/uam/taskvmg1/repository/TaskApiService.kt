package com.uam.taskvmg1.repository

import com.uam.taskvmg1.model.TaskItem
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskApiService {
    @GET("/task/all")
    suspend fun findAll():
            Response<List<TaskItem>>

    @GET("/task/getId/{id}")
    suspend fun findById(@Path("id") id: Long):
            Response<TaskItem>

    @POST("/task/save")
    suspend fun save(@Body task: TaskItem):Response<TaskItem>
}