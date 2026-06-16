package com.uam.taskvmg1.service

import com.uam.taskvmg1.model.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskApiService {
    @GET("task/all")
    suspend fun findAll():
            Response<List<Task>>

    @GET("task/getId/{id}")
    suspend fun findById(@Path("id") id: Int):
            Response<Task>

    @POST("task/save")
    suspend fun save(@Body task: Task): Response<Task>
}