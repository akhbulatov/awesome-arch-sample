package com.example.awesomearchsample.core.commonimpl.di

import com.example.awesomearchsample.core.common.error.ErrorHandler
import com.example.awesomearchsample.core.commonimpl.error.ErrorHandlerImpl
import kotlinx.serialization.json.Json

class CommonImplFactory(
    private val json: Json
) {

     val errorHandler: ErrorHandler by lazy {
        ErrorHandlerImpl(
            json = json
        )
    }
}
