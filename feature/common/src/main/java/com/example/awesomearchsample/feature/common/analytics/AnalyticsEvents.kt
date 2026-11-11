package com.example.awesomearchsample.feature.common.analytics

import com.example.awesomearchsample.core.analytics.api.AnalyticsEvent
import com.example.awesomearchsample.core.analytics.api.AnalyticsParam

object AnalyticsEvents {

    object Repo {

        data class RepoClick(
            val repoId: Long,
            val repoName: String
        ) : AnalyticsEvent {

            override val name: String = "repo_click"
            override val params: Map<String, AnalyticsParam> = mapOf(
                "repo_id" to AnalyticsParam.Text(repoId.toString()),
                "repo_name" to AnalyticsParam.Text(repoName)
            )
        }
    }
}
