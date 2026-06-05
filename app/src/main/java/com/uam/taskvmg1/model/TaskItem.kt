package com.uam.taskvmg1.model

data class TaskItem(
    val completed: Boolean,
    val description: String,
    val id: String,
    val title: String
)