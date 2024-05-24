package com.example.awesomearchsample.core.analytics.internal.firebase

import android.os.Bundle
import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import com.example.awesomearchsample.core.analytics.api.AnalyticsEvents

internal fun AnalyticsEvent.mapFrom() = FirebaseEvent(
    name = name,
    params = when (this) {
        is AnalyticsEvents.Repo.RepoClick -> this.mapParams()
        else -> Bundle()
    }
)

private fun AnalyticsEvents.Repo.RepoClick.mapParams() = Bundle().apply {
    putString("repo_id", repoId.toString())
    putString("repo_name", repoName)
}
