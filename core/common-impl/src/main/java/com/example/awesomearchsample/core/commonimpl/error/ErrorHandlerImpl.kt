package com.example.awesomearchsample.core.commonimpl.error

import com.example.awesomearchsample.core.common.error.ErrorEntity
import com.example.awesomearchsample.core.common.error.ErrorHandler
import com.example.awesomearchsample.core.network.error.NetworkErrorResponseParser
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.bodyAsText
import kotlinx.io.IOException

class ErrorHandlerImpl(
    private val errorResponseParser: NetworkErrorResponseParser
) : ErrorHandler {

    override suspend fun getError(throwable: Throwable): ErrorEntity = when (throwable) {
        is IOException -> ErrorEntity.Network
        is ClientRequestException -> {
            when (throwable.response.status.value) {
                in 400..499 -> {
                    val response = throwable.response.bodyAsText()
                    val errorNetModel = errorResponseParser.parseError(response)
                    when {
                        errorNetModel != null -> {
                            val message = errorNetModel.message
                            if (!message.isNullOrBlank()) {
                                ErrorEntity.Message(message)
                            } else {
                                ErrorEntity.Unknown
                            }
                        }
                        else -> ErrorEntity.Unknown
                    }
                }
                in 500..599 -> ErrorEntity.InternalNetwork
                else -> ErrorEntity.Unknown
            }
        }
        else -> ErrorEntity.Unknown
    }

    override fun recordError(throwable: Throwable) {
        // Запись исключений (мб некритичных) в Firebase Crashlytics, например
//        Firebase.crashlytics.recordException(throwable)
    }
}