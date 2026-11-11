package com.example.awesomearchsample.feature.common.di

import com.example.awesomearchsample.core.analytics.api.AnalyticsClient
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender

class CommonFeatureFactory(
    analyticsClientProvider: () -> AnalyticsClient
) {

    val analyticsEventSender: AnalyticsEventSender by lazy {
        AnalyticsEventSender(analyticsClient = analyticsClientProvider())
    }
}
