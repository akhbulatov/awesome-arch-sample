package com.example.awesomearchsample.core.analytics.internal.integration.firebase

import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import com.example.awesomearchsample.core.analytics.api.AnalyticsParam
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotSame
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class FirebaseEventMapperTest {

    @Test
    fun toFirebaseParams_preservesValuesAndTypes() {
        val event = FakeAnalyticsEvent(
            params = mapOf(
                "text" to AnalyticsParam.Text("value"),
                "integer" to AnalyticsParam.Integer(42L),
                "decimal" to AnalyticsParam.Decimal(1.5)
            )
        )

        val params = event.toFirebaseParams()

        assertEquals(3, params.size())
        assertEquals("value", params.getString("text"))
        assertEquals(42L, params.getLong("integer"))
        assertEquals(1.5, params.getDouble("decimal"), 0.0)
    }

    @Test
    fun toFirebaseParams_createsIndependentBundles() {
        val event = FakeAnalyticsEvent(params = mapOf("key" to AnalyticsParam.Text("original")))

        val first = event.toFirebaseParams()
        first.putString("key", "changed")
        val second = event.toFirebaseParams()

        assertNotSame(first, second)
        assertEquals("original", second.getString("key"))
        assertEquals(AnalyticsParam.Text("original"), event.params["key"])
    }

    private class FakeAnalyticsEvent(
        override val params: Map<String, AnalyticsParam>
    ) : AnalyticsEvent {
        override val name: String = "test_event"
    }
}
