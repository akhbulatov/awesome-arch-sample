package com.example.awesomearchsample.core.commonimpl.error

import com.example.awesomearchsample.core.common.error.ErrorEntity
import com.example.awesomearchsample.core.common.error.ErrorHandler
import com.example.awesomearchsample.core.network.error.ErrorNetModel
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CancellationException
import kotlinx.io.IOException
import kotlinx.serialization.json.Json

class ErrorHandlerImpl(
    private val json: Json
) : ErrorHandler {

    override suspend fun getError(throwable: Throwable): ErrorEntity = when (throwable) {
        is CancellationException -> throw throwable
        is IOException -> ErrorEntity.Network
        is ResponseException -> {
            val statusCode = throwable.response.status.value
            when (statusCode) {
                401, 403 -> ErrorEntity.AuthRequired
                in 400..499 -> {
                    val responseText = try {
                        throwable.response.bodyAsText()
                    } catch (e: Exception) {
                        null
                    }
                    val errorNetModel = parseError(responseText)
                    errorNetModel.toErrorEntity()
                }
                in 500..599 -> ErrorEntity.InternalNetwork
                else -> ErrorEntity.Unknown
            }
        }
        else -> ErrorEntity.Unknown
    }

    private fun parseError(response: String?): ErrorNetModel? {
        if (response.isNullOrBlank()) {
            return null
        }
        return try {
            json.decodeFromString<ErrorNetModel>(response.trim())
        } catch (e: Exception) {
            null
        }
    }

    private fun ErrorNetModel?.toErrorEntity(): ErrorEntity {
        val message = this?.message
        return if (!message.isNullOrBlank()) {
            ErrorEntity.Message(message)
        } else {
            ErrorEntity.Unknown
        }
    }

    override fun recordError(throwable: Throwable) {
        // Запись исключений (мб некритичных) в Firebase Crashlytics, например
//        Firebase.crashlytics.recordException(throwable)
    }
}
