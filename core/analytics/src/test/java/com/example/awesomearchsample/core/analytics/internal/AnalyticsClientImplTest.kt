package com.example.awesomearchsample.core.analytics.internal

import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import com.example.awesomearchsample.core.analytics.api.AnalyticsParam
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AnalyticsClientImplTest {

    @Test
    fun sendEvent_sendsToAllAnalytics() {
        // Arrange
        val first = FakeAnalytics()
        val second = FakeAnalytics()
        val client = AnalyticsClientImpl(analyticsSet = setOf(first, second))
        val event = FakeAnalyticsEvent(name = "test_event")

        // Act
        client.sendEvent(event)

        // Assert
        assertEquals(1, first.callCount)
        assertSame(event, first.lastEvent)
        assertEquals(1, second.callCount)
        assertSame(event, second.lastEvent)
    }

    @Test
    fun sendEvent_continuesWhenFirstProviderThrows() {
        val first = FakeAnalytics(failOnSend = true)
        val second = FakeAnalytics()
        val client = AnalyticsClientImpl(analyticsSet = setOf(first, second))
        val event = FakeAnalyticsEvent(name = "test_event")

        client.sendEvent(event)

        assertEquals(1, first.callCount)
        assertEquals(1, second.callCount)
        assertSame(event, second.lastEvent)
    }

    private class FakeAnalytics(
        private val failOnSend: Boolean = false
    ) : Analytics {
        var callCount = 0
            private set
        var lastEvent: AnalyticsEvent? = null
            private set

        override fun init() {
            error("Not used in this test")
        }

        override fun sendEvent(event: AnalyticsEvent) {
            callCount += 1
            check(!failOnSend) { "Event delivery failed" }
            lastEvent = event
        }
    }

    private class FakeAnalyticsEvent(
        override val name: String
    ) : AnalyticsEvent {
        override val params: Map<String, AnalyticsParam> = emptyMap()
    }
}
