package com.uam.taskvmg1.vmodel

import com.uam.taskvmg1.model.Task

interface TaskListState {
    data object Loading : TaskListState

    data class Success(
        val tasks: List<Task>
    ): TaskListState

    data class Error(
        val message : String
    ): TaskListState
}