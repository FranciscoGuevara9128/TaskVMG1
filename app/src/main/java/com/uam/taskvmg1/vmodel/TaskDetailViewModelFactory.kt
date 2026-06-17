package com.uam.taskvmg1.vmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uam.taskvmg1.repository.TaskRepository

class TaskDetailViewModelFactory(private val repository: TaskRepository):

    @Suppress("UNCHECKED_CAST")
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskDetailViewModel(repository) as T
    }
}