package com.example.awesomearchsample.core.ui.error

import android.content.Context
import com.example.awesomearchsample.R
import com.example.awesomearchsample.core.error.ErrorEntity
import com.example.awesomearchsample.core.error.ErrorHandler
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UiErrorHandler @Inject constructor(
    @ApplicationContext private val context: Context,
    private val errorHandler: ErrorHandler,
) {

    fun proceed(error: Throwable, errorListener: (UiError) -> Unit = {}) {
        Timber.e(error)
        errorHandler.recordError(error)
        when (val errorEntity = errorHandler.getError(error)) {
            is ErrorEntity.AuthRequired -> errorListener(
                UiError(
                    title = context.getString(R.string.error_user_not_authorized),
                )
            )
            is ErrorEntity.Message -> errorListener(
                UiError(title = errorEntity.value)
            )
            is ErrorEntity.Network -> errorListener(
                UiError(
                    title = context.getString(R.string.error_network_title),
                )
            )
            is ErrorEntity.InternalNetwork -> errorListener(
                UiError(
                    title = context.getString(R.string.error_internal_title),
                )
            )
            is ErrorEntity.Unknown -> errorListener(
                UiError(
                    title = context.getString(R.string.error_unknown_title),
                )
            )
        }
    }
}