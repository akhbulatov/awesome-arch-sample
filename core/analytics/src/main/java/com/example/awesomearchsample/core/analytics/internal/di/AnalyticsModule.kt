package com.example.awesomearchsample.core.analytics.internal.di

import com.example.awesomearchsample.core.analytics.api.Analytics
import com.example.awesomearchsample.core.analytics.api.AnalyticsRepository
import com.example.awesomearchsample.core.analytics.internal.AnalyticsRepositoryImpl
import com.example.awesomearchsample.core.analytics.internal.firebase.AppFirebaseAnalytics
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AnalyticsModule {

    @Binds
    @Singleton
    abstract fun bindAnalyticsRepository(impl: AnalyticsRepositoryImpl): AnalyticsRepository

    @Binds
    @IntoSet
    abstract fun bindAppFirebaseAnalytics(analytics: AppFirebaseAnalytics): Analytics
}