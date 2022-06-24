package com.test.movies.domain.model

sealed class Result<out T>  {
    data class Success<T>(val data : T): Result<T>()
    data class Error<T>(val error: String): Result<T>()
    object Loading : Result<Nothing>()
}
