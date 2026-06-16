package com.uam.taskvmg1.vmodel

import com.uam.taskvmg1.model.Task

interface TaskDetailState {
    data object Loading : TaskDetailState

    data class Success(
        val task: Task
    ): TaskDetailState

    data class Error(
        val message : String
    ):TaskDetailState
}