package com.example.awesomearchsample.core.corefactory.di

import android.content.Context
import com.example.awesomearchsample.core.analytics.di.AnalyticsFactory
import com.example.awesomearchsample.core.common.app.AppInitializers
import com.example.awesomearchsample.core.commonimpl.di.CommonImplFactory
import com.example.awesomearchsample.core.network.di.NetworkFactory
import com.example.awesomearchsample.core.preferences.di.PreferencesFactory
import com.example.awesomearchsample.core.serialization.di.SerializationFactory
import com.example.awesomearchsample.core.ui.di.UiFactory

class CoreFactory(
    val context: Context
) {

    private val commonImplFactory: CommonImplFactory by lazy {
        CommonImplFactory(
            json = serializationFactory.json
        )
    }

    private val serializationFactory: SerializationFactory by lazy {
        SerializationFactory()
    }

    val networkFactory: NetworkFactory by lazy {
        NetworkFactory(
            json = serializationFactory.json
        )
    }

    val preferencesFactory: PreferencesFactory by lazy {
        PreferencesFactory(
            context = context
        )
    }

    val uiFactory: UiFactory by lazy {
        UiFactory(
            errorHandler = commonImplFactory.errorHandler
        )
    }

    val analyticsFactory: AnalyticsFactory by lazy {
        AnalyticsFactory(
            context = context
        )
    }

    val appInitializers: AppInitializers by lazy {
        AppInitializers(
            initializers = setOf(analyticsFactory.analyticsInitializer)
        )
    }
}
