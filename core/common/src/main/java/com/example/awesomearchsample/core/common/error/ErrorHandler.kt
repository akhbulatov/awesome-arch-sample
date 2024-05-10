package com.example.awesomearchsample.core.common.error

import com.example.awesomearchsample.core.network.error.NetworkErrorResponseParser
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.errors.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorHandler @Inject constructor(
    private val errorResponseParser: NetworkErrorResponseParser
) {

    suspend fun getError(throwable: Throwable): ErrorEntity = when (throwable) {
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

    fun recordError(throwable: Throwable) {
        // Запись исключений (мб некритичных) в Firebase Crashlytics, например
//        Firebase.crashlytics.recordException(throwable)
    }
}