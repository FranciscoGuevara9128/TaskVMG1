package com.uam.taskvmg1.vmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.uam.taskvmg1.model.Task
import com.uam.taskvmg1.repository.TaskRepositoryOld


class TaskViewModel : ViewModel() {

    private val repository = TaskRepositoryOld()

    var tasks by mutableStateOf(listOf<Task>())
        private set
    var id by mutableStateOf("")
        private set
    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set
    var completed by mutableStateOf(false)
        private set


    init{
        loadTask()
    }

    private fun loadTask(){
        tasks = repository.getTask()
    }

    fun loadTask(taskId: String){
        if (taskId == ""){
            clearForm()
            return
        } else {
            val task = repository.getTaskById(taskId)
            task?.let {
                id = it.id
                title = it.title
                description = it.description
                completed = it.completed
            }
        }
    }

    fun getTaskId(taskId: String): Task? {
        return repository.getTaskById(taskId)
    }

    fun clearForm(){
        id = ""
        title = ""
        description = ""
        completed = false
    }

    fun addTask(task: Task) {
        repository.addTask(task)
        loadTask()
    }

    fun deleteTask(taskId: String){
        repository.deleteTask(taskId)
        loadTask()
    }

    fun updateTask(task: Task){
        repository.updateTask(task)
        loadTask()
    }

    fun onIdChange(value: String){
        id = value
    }

    fun onTitleChange(value: String){
        title = value
    }

    fun onCompletedChange(value: Boolean){
        completed = value
    }

    fun onDescriptionChange(value: String){
        description = value
    }
}