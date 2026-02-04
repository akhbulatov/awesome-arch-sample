package com.example.awesomearchsample.core.analytics.internal.integration.firebase

import android.os.Bundle
import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FirebaseEventMapperTest {

    @Test
    fun toFirebaseEvent_mapsFieldsCorrectly() {
        // Arrange
        val params = Bundle().apply {
            putString("key", "value")
        }
        val event = FakeAnalyticsEvent(
            name = "test_event",
            params = params
        )

        // Act
        val firebaseEvent = event.toFirebaseEvent()

        // Assert
        assertEquals("test_event", firebaseEvent.name)
        assertSame(params, firebaseEvent.params)
    }

    private class FakeAnalyticsEvent(
        override val name: String,
        override val params: Bundle
    ) : AnalyticsEvent
}
