package com.example.awesomearchsample.core.analytics.internal

import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import com.example.awesomearchsample.core.analytics.api.AnalyticsClient
import com.example.awesomearchsample.core.common.util.AppLogger

internal class AnalyticsClientImpl(
    private val analyticsSet: Set<Analytics>,
) : AnalyticsClient {

    override fun sendEvent(event: AnalyticsEvent) {
        AppLogger.d("Sending event: $event")
        analyticsSet.forEach { it.sendEvent(event) }
    }
}
