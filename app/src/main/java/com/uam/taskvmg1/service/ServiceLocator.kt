package com.uam.taskvmg1.service

import com.uam.taskvmg1.repository.TaskRepository

object ServiceLocator {
    private val taskApi = RetrofitClient.taskApiService

    val  repository = TaskRepository(taskApi)
}