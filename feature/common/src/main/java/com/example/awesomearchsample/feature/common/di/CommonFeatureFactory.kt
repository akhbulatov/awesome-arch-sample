package com.example.awesomearchsample.feature.common.di

import com.example.awesomearchsample.core.analytics.di.AnalyticsFactory
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender

class CommonFeatureFactory(
    analyticsFactory: AnalyticsFactory
) {

    val analyticsEventSender: AnalyticsEventSender = AnalyticsEventSender(
        analyticsClient = analyticsFactory.analyticsClient
    )
}
