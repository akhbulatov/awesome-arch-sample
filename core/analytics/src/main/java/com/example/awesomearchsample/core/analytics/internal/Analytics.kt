package com.example.awesomearchsample.core.analytics.internal

import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent

internal interface Analytics {
    fun init()

    fun sendEvent(event: AnalyticsEvent)
}
