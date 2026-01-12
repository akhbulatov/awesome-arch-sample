package com.example.awesomearchsample.core.analytics.di

import android.content.Context
import com.example.awesomearchsample.core.analytics.api.AnalyticsClient
import com.example.awesomearchsample.core.analytics.internal.Analytics
import com.example.awesomearchsample.core.analytics.internal.AnalyticsInitializer
import com.example.awesomearchsample.core.analytics.internal.AnalyticsClientImpl
import com.example.awesomearchsample.core.analytics.internal.integration.firebase.AppFirebaseAnalytics
import com.example.awesomearchsample.core.common.app.AppInitializer

class AnalyticsFactory(
    private val context: Context
) {

    private val analyticsSet: Set<Analytics> by lazy {
        setOf(
            appFirebaseAnalytics
        )
    }

    private val appFirebaseAnalytics: Analytics by lazy {
        AppFirebaseAnalytics(
            context = context
        )
    }

    val analyticsClient: AnalyticsClient by lazy {
        AnalyticsClientImpl(
            analyticsSet = analyticsSet
        )
    }

    val analyticsInitializer: AppInitializer by lazy {
        AnalyticsInitializer(
            analyticsSet = analyticsSet
        )
    }
}
