package com.example.awesomearchsample.feature.common.analytics

import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import com.example.awesomearchsample.core.analytics.api.SendAnalyticsEventUseCase
import com.example.awesomearchsample.core.common.util.AppLogger
import javax.inject.Inject

class AnalyticsEventSender @Inject constructor(
    private val sendAnalyticsEventUseCase: SendAnalyticsEventUseCase,
) {

    fun sendEvent(event: AnalyticsEvent) {
        try {
            sendAnalyticsEventUseCase.invoke(event)
        } catch (e: Exception) {
            AppLogger.e("Failed to send event=${event.name}: $e")
        }
    }
}