package com.example.awesomearchsample.core.analytics.internal.integration.firebase

import android.os.Bundle
import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import com.example.awesomearchsample.core.analytics.api.AnalyticsParam

internal fun AnalyticsEvent.toFirebaseParams(): Bundle = Bundle().apply {
    for ((key, param) in params) {
        when (param) {
            is AnalyticsParam.Text -> putString(key, param.value)
            is AnalyticsParam.Integer -> putLong(key, param.value)
            is AnalyticsParam.Decimal -> putDouble(key, param.value)
        }
    }
}
