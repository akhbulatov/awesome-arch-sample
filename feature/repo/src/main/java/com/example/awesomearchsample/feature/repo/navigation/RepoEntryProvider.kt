package com.example.awesomearchsample.feature.repo.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.repo.di.RepoFeatureDependencies

fun EntryProviderScope<NavKey>.addRepoEntries(
    repoFeatureDependencies: RepoFeatureDependencies,
    navigator: AppNavigator,
    onNavigateToSearch: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onNavigateToUserDetails: (String) -> Unit
) {
    addReposEntry(
        repoFeatureDependencies = repoFeatureDependencies,
        navigator = navigator,
        onNavigateToSearch = onNavigateToSearch,
        onNavigateToSettings = onNavigateToSettings
    )
    addRepoDetailsEntry(
        repoFeatureDependencies = repoFeatureDependencies,
        navigator = navigator,
        onNavigateToUserDetails = onNavigateToUserDetails
    )
}
