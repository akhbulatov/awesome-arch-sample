package com.example.awesomearchsample.feature.repo.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.repo.di.RepoFeatureDependencies
import com.example.awesomearchsample.feature.repo.repos.ReposScreen

internal fun EntryProviderScope<NavKey>.addReposEntry(
    repoFeatureDependencies: RepoFeatureDependencies,
    navigator: AppNavigator,
    onNavigateToSearch: () -> Unit,
    onNavigateToSettings: () -> Unit
) {
    entry<ReposRoute> {
        ReposScreen(
            dependencies = repoFeatureDependencies.reposScreenDependencies,
            onNavigateToSearch = onNavigateToSearch,
            onNavigateToSettings = onNavigateToSettings,
            onNavigateToRepoDetails = navigator::navigateToRepoDetails
        )
    }
}
