package com.example.awesomearchsample.feature.common.analytics

import android.os.Bundle
import com.example.awesomearchsample.core.analytics.api.AnalyticsClient
import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AnalyticsEventSenderTest {

    @Test
    fun sendEvent_callsClient() {
        // Arrange
        val client = FakeAnalyticsClient()
        val sender = AnalyticsEventSender(client)
        val event = FakeAnalyticsEvent("event")

        // Act
        sender.sendEvent(event)

        // Assert
        assertEquals(1, client.callCount)
        assertEquals(event, client.lastEvent)
    }

    @Test
    fun sendEvent_swallowException() {
        // Arrange
        val client = ThrowingAnalyticsClient()
        val sender = AnalyticsEventSender(client)

        // Act + Assert
        try {
            sender.sendEvent(FakeAnalyticsEvent("event"))
        } catch (e: Exception) {
            fail("Expected no exception, got: $e")
        }
    }

    private class FakeAnalyticsClient : AnalyticsClient {
        var callCount = 0
            private set
        var lastEvent: AnalyticsEvent? = null
            private set

        override fun sendEvent(event: AnalyticsEvent) {
            callCount += 1
            lastEvent = event
        }
    }

    private class ThrowingAnalyticsClient : AnalyticsClient {
        override fun sendEvent(event: AnalyticsEvent) {
            throw IllegalStateException("boom")
        }
    }

    private class FakeAnalyticsEvent(
        override val name: String
    ) : AnalyticsEvent {
        override val params = Bundle()
    }
}
