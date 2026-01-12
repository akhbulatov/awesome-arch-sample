package com.example.awesomearchsample.core.analytics.internal.firebase

import android.content.Context
import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import com.example.awesomearchsample.core.analytics.internal.Analytics

//import com.google.firebase.analytics.FirebaseAnalytics

internal class AppFirebaseAnalytics(
    context: Context
) : Analytics {

//    private val firebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    override fun init() {}

    override fun sendEvent(event: AnalyticsEvent) {
        val firebaseEvent = event.mapFrom()
//        firebaseAnalytics.logEvent(firebaseEvent.name, firebaseEvent.params)
    }
}
