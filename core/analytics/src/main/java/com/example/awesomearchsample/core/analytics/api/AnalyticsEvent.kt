package com.example.awesomearchsample.core.analytics.api

import android.os.Bundle

interface AnalyticsEvent {
    val name: String
    val params: Bundle
}
