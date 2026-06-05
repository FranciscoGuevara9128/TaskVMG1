package com.uam.taskvmg1.navigation

import kotlinx.serialization.Serializable

@Serializable

object TaskList{
}

@Serializable
data class TaskDetail(val taskId: Int){
}

