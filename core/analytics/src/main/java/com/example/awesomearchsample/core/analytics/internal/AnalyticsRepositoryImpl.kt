package com.example.awesomearchsample.core.analytics.internal

import com.example.awesomearchsample.core.analytics.api.Analytics
import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import com.example.awesomearchsample.core.analytics.api.AnalyticsRepository
import com.example.awesomearchsample.core.common.util.AppLogger

internal class AnalyticsRepositoryImpl(
    private val analyticsSet: Set<Analytics>,
) : AnalyticsRepository {

    override fun sendEvent(event: AnalyticsEvent) {
        AppLogger.d("Sending event: $event")
        analyticsSet.forEach { it.sendEvent(event) }
    }
}