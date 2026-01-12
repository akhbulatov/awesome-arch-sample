package com.example.awesomearchsample.feature.common.di

import com.example.awesomearchsample.core.corefactory.di.CoreFactory
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender

class CommonFeatureFactory(
    coreFactory: CoreFactory
) {

    val analyticsEventSender: AnalyticsEventSender = AnalyticsEventSender(
        analyticsClient = coreFactory.analyticsFactory.analyticsClient
    )
}
