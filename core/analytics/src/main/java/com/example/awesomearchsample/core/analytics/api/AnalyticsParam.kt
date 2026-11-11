package com.example.awesomearchsample.core.analytics.api

sealed interface AnalyticsParam {
    data class Text(val value: String) : AnalyticsParam
    data class Integer(val value: Long) : AnalyticsParam
    data class Decimal(val value: Double) : AnalyticsParam
}
