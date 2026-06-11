package com.uam.taskvmg1.repository

import com.uam.taskvmg1.model.TaskItem
import com.uam.taskvmg1.service.ApiResult

class TaskRepository(private val api:TaskApiService) {
    private val tasks = mutableListOf<TaskItem>(
        TaskItem(
            id = "1",
            title = "Task 1",
            description = "Description for Task 1",
            completed = false
        ),
        TaskItem(
            id = "2",
            title = "Task 2",
            description = "Description for Task 2",
            completed = false
        ),
        TaskItem(
            id = "3",
            title = "Task 3",
            description = "Description for Task 3",
            completed = false
        )
    )

    suspend fun findAll(): ApiResult<List<TaskItem>> {
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

    // Implementación de findById. id en TaskItem está en String, pero en Task es Int, por lo que se debe convertir el id a Long antes de llamar a la función findById del API

     suspend fun findById(id: Long): ApiResult<TaskItem> {
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

    fun getTask(): List<TaskItem> = tasks
    fun addTask(task: TaskItem) = tasks.add(task)
    fun deleteTask(task:TaskItem) = tasks.remove(task)
    fun getTaskById(id: String): TaskItem? = tasks.find { it.id == id }
    fun updateTask(task: TaskItem){
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task
        }
    }
    fun deleteTask(taskId: String){
        val task = getTaskById(taskId)
        task?.let {
            deleteTask(it)
        }
    }



}

