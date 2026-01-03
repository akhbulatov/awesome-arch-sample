package com.example.awesomearchsample.feature.repo.repos.di

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.domain.repo.usecase.GetReposUseCase
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender
import com.example.awesomearchsample.feature.repo.di.rememberRepoFeatureDependencies
import com.example.awesomearchsample.feature.repo.navigation.RepoNavigator

interface ReposDependencies {
    val repoNavigator: RepoNavigator
    val getReposUseCase: GetReposUseCase
    val uiErrorHandler: UiErrorHandler
    val analyticsEventSender: AnalyticsEventSender
}

@Composable
fun rememberReposDependencies(): ReposDependencies {
    return rememberRepoFeatureDependencies().reposDependencies
}
