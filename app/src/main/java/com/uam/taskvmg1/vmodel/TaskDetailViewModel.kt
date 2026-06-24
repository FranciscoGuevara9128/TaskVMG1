package com.uam.taskvmg1.vmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uam.taskvmg1.model.Task
import com.uam.taskvmg1.repository.TaskRepository
import com.uam.taskvmg1.service.ApiResult
import com.uam.taskvmg1.service.ServiceLocator.repository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskDetailViewModel(private val repository : TaskRepository): ViewModel()
{
    private val _state = MutableStateFlow<TaskDetailState>(
    TaskDetailState.Loading
    )
    /*private val _event = MutableSharedFlow<TaskDetailEvent>(
        TaskDetailEvent.Idle
    )*/
    val state = _state.asStateFlow()

    var id by mutableStateOf("")
        private set
    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set
    var completed by mutableStateOf(false)
        private set

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

   private fun clearForm(){
        id = ""
        title = ""
        description = ""
        completed = false
    }

    private fun loadForm(task : Task){
        id = task.id
        title = task.title
        description = task.description
        completed = task.completed
    }

    fun findById(
        id : String
    ){
        Log.i("TaskDetailViewModel", "findById called with id: $id")
        if (id.equals("0") ){
            Log.i("TaskDetailViewModel", "id is 0, clearing form")
            clearForm()
            return
        }
        _state.value = TaskDetailState.Loading
        Log.i("TaskDetailViewModel", "Finding task with id: $id")
        viewModelScope.launch{
            when(
                val result = repository.findById(id)
            )
            {
                is ApiResult.Success -> {
                    _state.value = TaskDetailState.Success(result.data)
                    loadForm(result.data)
                    Log.i("TaskDetailViewModel", "Task found: ${result.data}")
                }
                is ApiResult.Error -> _state.value = TaskDetailState.Error(result.message)
            }
        }
    }
    fun save(){
        val task = Task(
            id = id,
            title = title,
            description = description,
            completed = completed
        )
        _state.value = TaskDetailState.Loading
        viewModelScope.launch{
            when(
                val result = repository.save(task)
            )
            {
                is ApiResult.Success -> _state.value = TaskDetailState.Success(result.data)
                is ApiResult.Error -> _state.value = TaskDetailState.Error(result.message)
            }
        }
    }
}