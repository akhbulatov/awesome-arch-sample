package com.example.awesomearchsample.core.analytics.internal

import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
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

    @Test
    fun init_continuesWhenFirstProviderThrows() {
        val first = FakeAnalytics(failOnInit = true)
        val second = FakeAnalytics()
        val initializer = AnalyticsInitializer(setOf(first, second))

        initializer.init()

        assertEquals(1, first.initCount)
        assertEquals(1, second.initCount)
    }

    private class FakeAnalytics(
        private val failOnInit: Boolean = false
    ) : Analytics {
        var initCount = 0
            private set

        override fun init() {
            initCount += 1
            check(!failOnInit) { "Initialization failed" }
        }

        override fun sendEvent(event: AnalyticsEvent) {
            error("Not used in this test")
        }
    }
}
