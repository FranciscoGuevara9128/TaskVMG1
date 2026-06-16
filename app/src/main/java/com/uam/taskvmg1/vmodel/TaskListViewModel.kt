package com.uam.taskvmg1.vmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uam.taskvmg1.repository.TaskRepository
import com.uam.taskvmg1.service.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val repository : TaskRepository)
    : ViewModel() {
    private val _state=
        MutableStateFlow<TaskListState>(TaskListState.Loading)

    val state = _state.asStateFlow()

    init{
        findAll()
    }

    private fun findAll(){
        viewModelScope.launch{
            _state.value = TaskListState.Loading
            when (val result = repository.findAll()){
                is ApiResult.Success ->
                    _state.value = TaskListState.Success(result.data)
                is ApiResult.Error ->
                    _state.value = TaskListState.Error(result.message)
            }
        }
    }
}