package com.example.awesomearchsample.feature.common.analytics

import com.example.awesomearchsample.core.analytics.api.AnalyticsParam
import org.junit.Assert.assertEquals
import org.junit.Test

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
        assertEquals(AnalyticsParam.Text(repoId.toString()), event.params["repo_id"])
        assertEquals(AnalyticsParam.Text(repoName), event.params["repo_name"])
    }
}
