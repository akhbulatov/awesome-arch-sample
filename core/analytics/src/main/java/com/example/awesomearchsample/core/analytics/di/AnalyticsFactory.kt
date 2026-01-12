package com.example.awesomearchsample.core.analytics.di

import android.content.Context
import com.example.awesomearchsample.core.analytics.api.Analytics
import com.example.awesomearchsample.core.analytics.api.AnalyticsRepository
import com.example.awesomearchsample.core.analytics.internal.AnalyticsInitializer
import com.example.awesomearchsample.core.analytics.internal.AnalyticsRepositoryImpl
import com.example.awesomearchsample.core.analytics.internal.firebase.AppFirebaseAnalytics
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

    val analyticsRepository: AnalyticsRepository by lazy {
        AnalyticsRepositoryImpl(
            analyticsSet = analyticsSet
        )
    }

    val analyticsInitializer: AppInitializer by lazy {
        AnalyticsInitializer(
            analyticsSet = analyticsSet
        )
    }
}
