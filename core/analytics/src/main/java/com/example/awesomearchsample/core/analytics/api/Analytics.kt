package com.example.awesomearchsample.core.analytics.api

interface Analytics {
    fun init()

    fun sendEvent(event: AnalyticsEvent)
}
