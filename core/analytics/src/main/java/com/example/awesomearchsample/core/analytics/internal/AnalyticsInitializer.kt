package com.example.awesomearchsample.core.analytics.internal

import com.example.awesomearchsample.core.commonapi.app.AppInitializer
import com.example.awesomearchsample.core.commonapi.util.AppLogger

internal class AnalyticsInitializer(
    private val analyticsSet: Set<Analytics>
) : AppInitializer {

    override fun init() {
        analyticsSet.forEach { analytics ->
            try {
                analytics.init()
            } catch (error: Exception) {
                AppLogger.e("Failed to initialize analytics provider ${analytics.javaClass.simpleName}", error)
            }
        }
    }
}
