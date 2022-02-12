package com.example.dailyplanner.data.base

sealed class Result<out T> {
    data class Success<out R>(val data: R) : Result<R>()
    data class Error(val throwable: Exception?) : Result<Nothing>()
}

inline fun <reified T> Result<T>.data(): T? {
    return if (this is Result.Success) {
        this.data
    } else {
        null
    }
}