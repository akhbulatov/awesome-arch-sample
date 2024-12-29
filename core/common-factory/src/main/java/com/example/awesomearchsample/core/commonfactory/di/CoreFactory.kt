package com.example.awesomearchsample.core.commonfactory.di

import android.content.Context
import com.example.awesomearchsample.core.analytics.di.AnalyticsFactory
import com.example.awesomearchsample.core.commonimpl.di.CommonImplFactory
import com.example.awesomearchsample.core.network.di.NetworkFactory
import com.example.awesomearchsample.core.preferences.di.PreferencesFactory
import com.example.awesomearchsample.core.ui.di.UiFactory

class CoreFactory(
    val context: Context
) {

    private val commonImplFactory: CommonImplFactory by lazy {
        CommonImplFactory(
            networkErrorResponseParser = networkFactory.networkErrorResponseParser
        )
    }

    val networkFactory: NetworkFactory by lazy {
        NetworkFactory()
    }

    val preferencesFactory: PreferencesFactory by lazy {
        PreferencesFactory(
            context = context
        )
    }

    val uiFactory: UiFactory by lazy {
        UiFactory(
            context = context,
            errorHandler = commonImplFactory.errorHandler
        )
    }

    val analyticsFactory: AnalyticsFactory by lazy {
        AnalyticsFactory(
            context = context
        )
    }
}