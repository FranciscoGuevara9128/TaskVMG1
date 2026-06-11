package com.uam.taskvmg1.service

sealed interface ApiResult <out T>
{
data class Success<T>(val data: T) : ApiResult<T>
data class Error(val message : String): ApiResult<Nothing>

}