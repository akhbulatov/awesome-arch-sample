package com.example.awesomearchsample.feature.common.analytics

import android.os.Bundle
import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent

object AnalyticsEvents {

    object Repo {

        data class RepoClick(
            val repoId: Long,
            val repoName: String
        ) : AnalyticsEvent {

            override val name: String = "repo_click"
            override val params: Bundle = Bundle().apply {
                putString("repo_id", repoId.toString())
                putString("repo_name", repoName)
            }
        }
    }
}
