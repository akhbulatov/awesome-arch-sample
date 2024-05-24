package com.example.awesomearchsample.core.analytics.api

object AnalyticsEvents {
    object Repo {
        data class RepoClick(
            val repoId: Long,
            val repoName: String
        ) : AnalyticsEvent(name = "repo_click")
    }
}