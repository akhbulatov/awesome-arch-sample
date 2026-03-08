package com.example.awesomearchsample.core.commonapi.error

interface ErrorHandler {
    suspend fun getError(throwable: Throwable): ErrorEntity

    fun recordError(throwable: Throwable)
}