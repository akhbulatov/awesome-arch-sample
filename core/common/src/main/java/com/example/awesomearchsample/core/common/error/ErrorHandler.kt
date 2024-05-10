package com.example.awesomearchsample.core.common.error

interface ErrorHandler {
    suspend fun getError(throwable: Throwable): ErrorEntity

    fun recordError(throwable: Throwable)
}