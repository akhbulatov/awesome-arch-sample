package com.example.awesomearchsample.core.analytics.internal.firebase

import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent

internal fun AnalyticsEvent.mapFrom() = FirebaseEvent(
    name = name,
    params = params
)
