package com.example.awesomearchsample.feature.repo.repos.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.util.getApplicationInstance
import com.example.awesomearchsample.domain.repo.usecase.GetReposUseCase
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender
import com.example.awesomearchsample.feature.repo.navigation.RepoNavigator

interface ReposDependencies {
    val repoNavigator: RepoNavigator
    val getReposUseCase: GetReposUseCase
    val uiErrorHandler: UiErrorHandler
    val analyticsEventSender: AnalyticsEventSender
}

interface ReposDependenciesProvider {
    fun getReposDependencies(): ReposDependencies
}

@Composable
fun rememberReposDependencies(): ReposDependencies {
    val application = getApplicationInstance()
    return remember { (application as ReposDependenciesProvider).getReposDependencies() }
}
