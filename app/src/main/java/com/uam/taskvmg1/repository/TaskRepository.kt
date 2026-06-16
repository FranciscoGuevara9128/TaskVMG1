package com.uam.taskvmg1.repository

import com.uam.taskvmg1.model.Task
import com.uam.taskvmg1.service.ApiResult
import com.uam.taskvmg1.service.TaskApiService

class TaskRepository(private val api: TaskApiService) {
    private val tasks = mutableListOf<Task>(
        Task(
            id = 1,
            title = "Task 1",
            description = "Description for Task 1",
            completed = false
        ),
        Task(
            id =2,
            title = "Task 2",
            description = "Description for Task 2",
            completed = false
        ),
        Task(
            id = 3,
            title = "Task 3",
            description = "Description for Task 3",
            completed = false
        )
    )

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

    fun getTask(): List<Task> = tasks
    fun addTask(task: Task) = tasks.add(task)
    fun deleteTask(task:Task) = tasks.remove(task)
    fun getTaskById(id: Int): Task? = tasks.find { it.id == id }
    fun updateTask(task: Task){
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task
        }
    }
    fun deleteTask(taskId: Int){
        val task = getTaskById(taskId)
        task?.let {
            deleteTask(it)
        }
    }



}

