package com.uam.taskvmg1.vmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uam.taskvmg1.repository.TaskRepository

class TaskListViewModelFactory (private val repository: TaskRepository): ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return TaskListViewModel(repository) as T
    }
}