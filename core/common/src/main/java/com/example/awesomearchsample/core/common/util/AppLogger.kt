package com.example.awesomearchsample.core.common.util

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

object AppLogger {

    init {
        Napier.base(DebugAntilog())
    }

    fun d(message: String) {
        Napier.d(message = message)
    }

    fun e(throwable: Throwable) {
        Napier.e(throwable = throwable, message = { "" })
    }
}
