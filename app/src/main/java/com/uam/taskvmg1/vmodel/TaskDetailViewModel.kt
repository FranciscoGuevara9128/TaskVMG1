package com.uam.taskvmg1.vmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uam.taskvmg1.repository.TaskRepository
import com.uam.taskvmg1.service.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskDetailViewModel(private val repository : TaskRepository): ViewModel()
{
    private val _state = MutableStateFlow<TaskDetailState>(
    TaskDetailState.Loading
    )

    val state = _state.asStateFlow()

    fun findById(
        id : Int
    ){
        viewModelScope.launch{
            _state.value = TaskDetailState.Loading
            when(
                val result = repository.findById(id)
            )
            {
                is ApiResult.Success -> _state.value = TaskDetailState.Success(result.data)
                is ApiResult.Error -> _state.value = TaskDetailState.Error(result.message)
            }
        }
    }
}