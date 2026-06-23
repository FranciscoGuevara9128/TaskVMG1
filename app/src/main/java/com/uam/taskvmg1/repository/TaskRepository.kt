package com.uam.taskvmg1.repository

import com.uam.taskvmg1.model.Task
import com.uam.taskvmg1.service.ApiResult
import com.uam.taskvmg1.service.TaskApiService

class TaskRepository(private val api: TaskApiService) {

    suspend fun findAll(): ApiResult<List<Task>> {
        return try {
            val response = api.findAll()
            if (response.isSuccessful) {
                ApiResult.Success(response.body() ?: emptyList())
            } else {
                ApiResult.Error("Error HTTP: ${response.code()}}")
            }
        }catch(ex: Exception){
            ApiResult.Error("Error: ${ex.message}")
        }
    }

     suspend fun findById(id: Int): ApiResult<Task> {
        return try {
            val response = api.findById(id)
            if (response.isSuccessful) {
                    ApiResult.Success(response.body()!!)
            } else {
                ApiResult.Error("Error HTTP: ${response.code()}}")
            }
        }catch(ex: Exception){
            ApiResult.Error("Error: ${ex.message}")
        }
    }

    suspend fun save(task: Task): ApiResult<Task> {
        return try {
            val result = api.save(task)
            if (result.isSuccessful && result.body() != null) {
                ApiResult.Success(result.body()!!)
            } else {
                ApiResult.Error("Error HTTP: ${result.code()}")
            }
        }catch(ex: Exception){
            ApiResult.Error("Error: ${ex.message}")
        }
    }




}

