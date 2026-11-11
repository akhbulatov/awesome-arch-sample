package com.example.awesomearchsample.core.analytics.api

interface AnalyticsEvent {
    val name: String
    val params: Map<String, AnalyticsParam>
}
