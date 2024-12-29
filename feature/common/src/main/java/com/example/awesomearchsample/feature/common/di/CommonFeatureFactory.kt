package com.example.awesomearchsample.feature.common.di

import com.example.awesomearchsample.core.commonfactory.di.CoreFactory
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender

class CommonFeatureFactory(
    coreFactory: CoreFactory
) {

    val analyticsEventSender: AnalyticsEventSender = AnalyticsEventSender(
        analyticsRepository = coreFactory.analyticsFactory.analyticsRepository
    )
}