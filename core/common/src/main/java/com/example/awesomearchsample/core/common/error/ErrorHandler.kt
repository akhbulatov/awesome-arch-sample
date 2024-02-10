package com.example.awesomearchsample.core.common.error

import com.example.awesomearchsample.core.network.error.NetworkErrorResponseParser
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorHandler @Inject constructor(
    private val errorResponseParser: NetworkErrorResponseParser
) {

    fun getError(throwable: Throwable): ErrorEntity = when (throwable) {
        is IOException -> ErrorEntity.Network
        is HttpException -> {
            when (throwable.code()) {
                in 400..499 -> {
                    val response = throwable.response()?.errorBody()?.string()
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