package com.example.awesomearchsample.core.analytics.internal

import android.os.Bundle
import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
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
        val event = FakeAnalyticsEvent(
            name = "test_event",
            params = Bundle()
        )

        // Act
        client.sendEvent(event)

        // Assert
        assertEquals(1, first.callCount)
        assertSame(event, first.lastEvent)
        assertEquals(1, second.callCount)
        assertSame(event, second.lastEvent)
    }

    private class FakeAnalytics : Analytics {
        var callCount = 0
            private set
        var lastEvent: AnalyticsEvent? = null
            private set

        override fun init() {
            error("Not used in this test")
        }

        override fun sendEvent(event: AnalyticsEvent) {
            callCount += 1
            lastEvent = event
        }
    }

    private class FakeAnalyticsEvent(
        override val name: String,
        override val params: Bundle
    ) : AnalyticsEvent
}
