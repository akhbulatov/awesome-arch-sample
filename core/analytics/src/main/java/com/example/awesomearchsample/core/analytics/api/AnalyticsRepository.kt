package com.example.awesomearchsample.core.analytics.api

interface AnalyticsRepository {
    fun sendEvent(event: AnalyticsEvent)
}