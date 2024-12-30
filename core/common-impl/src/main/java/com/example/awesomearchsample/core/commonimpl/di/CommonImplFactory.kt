package com.example.awesomearchsample.core.commonimpl.di

import com.example.awesomearchsample.core.common.error.ErrorHandler
import com.example.awesomearchsample.core.commonimpl.error.ErrorHandlerImpl
import com.example.awesomearchsample.core.network.error.NetworkErrorResponseParser

class CommonImplFactory(
    private val networkErrorResponseParser: NetworkErrorResponseParser
) {

     val errorHandler: ErrorHandler by lazy {
        ErrorHandlerImpl(
            errorResponseParser = networkErrorResponseParser
        )
    }
}