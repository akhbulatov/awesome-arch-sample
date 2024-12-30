package com.example.awesomearchsample.core.ui.di

import android.content.Context
import com.example.awesomearchsample.core.common.error.ErrorHandler
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.util.ResourceManager

class UiFactory(
    private val context: Context,
    private val errorHandler: ErrorHandler
) {

    val uiErrorHandler: UiErrorHandler by lazy {
        UiErrorHandler(
            context = context,
            errorHandler = errorHandler
        )
    }

    val resourceManager: ResourceManager by lazy {
        ResourceManager(
            context = context
        )
    }
}