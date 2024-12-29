package com.example.awesomearchsample.feature.common.analytics

import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import com.example.awesomearchsample.core.analytics.api.AnalyticsRepository
import com.example.awesomearchsample.core.common.util.AppLogger

class AnalyticsEventSender(
    private val analyticsRepository: AnalyticsRepository,
) {

    fun sendEvent(event: AnalyticsEvent) {
        try {
            analyticsRepository.sendEvent(event)
        } catch (e: Exception) {
            AppLogger.e("Failed to send event=${event.name}: $e")
        }
    }
}