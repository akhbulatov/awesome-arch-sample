package com.example.awesomearchsample.core.ui.di

import com.example.awesomearchsample.core.common.error.ErrorHandler
import com.example.awesomearchsample.core.ui.error.UiErrorHandler

class UiFactory(
    private val errorHandler: ErrorHandler
) {

    val uiErrorHandler: UiErrorHandler by lazy {
        UiErrorHandler(
            errorHandler = errorHandler
        )
    }
}
