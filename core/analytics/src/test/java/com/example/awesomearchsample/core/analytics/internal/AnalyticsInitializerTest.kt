package com.example.awesomearchsample.core.analytics.internal

import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import org.junit.Assert.assertEquals
import org.junit.Test

class AnalyticsInitializerTest {

    @Test
    fun init_initializesAllAnalytics() {
        // Arrange
        val first = FakeAnalytics()
        val second = FakeAnalytics()
        val initializer = AnalyticsInitializer(setOf(first, second))

        // Act
        initializer.init()

        // Assert
        assertEquals(1, first.initCount)
        assertEquals(1, second.initCount)
    }

    private class FakeAnalytics : Analytics {
        var initCount = 0
            private set

        override fun init() {
            initCount += 1
        }

        override fun sendEvent(event: AnalyticsEvent) {
            error("Not used in this test")
        }
    }
}
