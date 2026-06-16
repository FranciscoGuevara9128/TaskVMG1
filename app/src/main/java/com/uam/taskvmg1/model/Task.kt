package com.uam.taskvmg1.model

data class Task(
    val completed: Boolean,
    val description: String,
    val id: Int,
    val title: String
)