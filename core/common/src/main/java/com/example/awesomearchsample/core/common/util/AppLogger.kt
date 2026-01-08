package com.example.awesomearchsample.core.common.util

import co.touchlab.kermit.Logger
import co.touchlab.kermit.platformLogWriter

object AppLogger {

    private val logger by lazy { Logger.withTag("App") }

    init {
        Logger.setLogWriters(platformLogWriter())
    }

    fun d(message: String) {
        logger.d { message }
    }

    fun e(throwable: Throwable) {
        logger.e(throwable) { "" }
    }

    fun e(message: String) {
        logger.e { message }
    }
}
