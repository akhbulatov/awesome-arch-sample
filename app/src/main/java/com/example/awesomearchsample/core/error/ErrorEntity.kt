package com.example.awesomearchsample.core.error

sealed class ErrorEntity {
    data object AuthRequired : ErrorEntity()
    data class Message(val value: String) : ErrorEntity()
    data object Network : ErrorEntity()
    data object InternalNetwork : ErrorEntity()
    data object Unknown : ErrorEntity()
}