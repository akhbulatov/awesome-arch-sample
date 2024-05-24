package com.example.awesomearchsample.core.analytics.api

import javax.inject.Inject

class SendAnalyticsEventUseCase @Inject constructor(
    private val analyticsRepository: AnalyticsRepository,
) {

    operator fun invoke(event: AnalyticsEvent) {
        analyticsRepository.sendEvent(event)
    }
}
