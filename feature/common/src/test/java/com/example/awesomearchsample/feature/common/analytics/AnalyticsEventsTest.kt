package com.example.awesomearchsample.feature.common.analytics

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AnalyticsEventsTest {

    @Test
    fun repoClickEvent_hasExpectedNameAndParams() {
        // Arrange
        val repoId = 42L
        val repoName = "Awesome"

        // Act
        val event = AnalyticsEvents.Repo.RepoClick(repoId, repoName)

        // Assert
        assertEquals("repo_click", event.name)
        assertEquals(repoId.toString(), event.params.getString("repo_id"))
        assertEquals(repoName, event.params.getString("repo_name"))
    }
}
