package com.example.awesomearchsample.feature.common.analytics

import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import com.example.awesomearchsample.core.analytics.api.AnalyticsClient
import com.example.awesomearchsample.core.common.util.AppLogger

class AnalyticsEventSender(
    private val analyticsClient: AnalyticsClient,
) {

    fun sendEvent(event: AnalyticsEvent) {
        try {
            analyticsClient.sendEvent(event)
        } catch (e: Exception) {
            AppLogger.e("Failed to send event=${event.name}: $e")
        }
    }
}
