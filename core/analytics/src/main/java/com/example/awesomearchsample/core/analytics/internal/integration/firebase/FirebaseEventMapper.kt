package com.example.awesomearchsample.core.analytics.internal.integration.firebase

import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent

internal fun AnalyticsEvent.toFirebaseEvent() = FirebaseEvent(
    name = name,
    params = params
)
