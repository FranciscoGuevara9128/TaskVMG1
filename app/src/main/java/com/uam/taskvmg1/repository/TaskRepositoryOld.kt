package com.uam.taskvmg1.repository

import com.uam.taskvmg1.model.Task
import com.uam.taskvmg1.service.ApiResult
import com.uam.taskvmg1.service.TaskApiService

class TaskRepositoryOld() {
    private val tasks = mutableListOf<Task>(  )

    fun getTask(): List<Task> = tasks
    fun addTask(task: Task) = tasks.add(task)
    fun deleteTask(task:Task) = tasks.remove(task)
    fun getTaskById(id: String): Task? = tasks.find { it.id.equals(id) }
    fun updateTask(task: Task){
        val index = tasks.indexOfFirst { it.id.equals(task.id) }
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

