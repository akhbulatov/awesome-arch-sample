package com.example.awesomearchsample.core.ui.error

import com.example.awesomearchsample.core.commonapi.error.ErrorEntity
import com.example.awesomearchsample.core.commonapi.error.ErrorHandler
import com.example.awesomearchsample.core.commonapi.util.AppLogger
import com.example.awesomearchsample.core.ui.R
import com.example.awesomearchsample.core.ui.text.UiText

class UiErrorHandler(
    private val errorHandler: ErrorHandler,
) {

    suspend fun proceed(error: Throwable, errorListener: (UiError) -> Unit = {}) {
        val errorEntity = errorHandler.getError(error)

        AppLogger.e(error)
        errorHandler.recordError(error)

        val uiError = errorEntity.toUiError()
        errorListener(uiError)
    }

    private fun ErrorEntity.toUiError(): UiError = when (this) {
        is ErrorEntity.AuthRequired -> UiError(
            title = UiText.Res(R.string.error_user_not_authorized),
        )
        is ErrorEntity.Message -> UiError(
            title = UiText.Plain(value)
        )
        is ErrorEntity.Network -> UiError(
            title = UiText.Res(R.string.error_network_title),
        )
        is ErrorEntity.InternalNetwork -> UiError(
            title = UiText.Res(R.string.error_internal_title),
        )
        is ErrorEntity.Unknown -> UiError(
            title = UiText.Res(R.string.error_unknown_title),
        )
    }
}
