package com.example.awesomearchsample.core.analytics.internal

import com.example.awesomearchsample.core.common.app.AppInitializer

internal class AnalyticsInitializer(
    private val analyticsSet: Set<Analytics>
) : AppInitializer {

    override fun init() {
        analyticsSet.forEach { it.init() }
    }
}
