package com.example.awesomearchsample.core.analytics.api

interface AnalyticsClient {
    fun sendEvent(event: AnalyticsEvent)
}
