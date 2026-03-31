package com.example.awesomearchsample.feature.repo.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.repo.di.RepoFeatureDependencies
import com.example.awesomearchsample.feature.repo.repodetails.RepoDetailsScreen

internal fun EntryProviderScope<NavKey>.addRepoDetailsEntry(
    repoFeatureDependencies: RepoFeatureDependencies,
    navigator: AppNavigator,
    onNavigateToUserDetails: (String) -> Unit
) {
    entry<RepoDetailsRoute> { route ->
        RepoDetailsScreen(
            route = route,
            dependencies = repoFeatureDependencies.repoDetailsScreenDependencies,
            onNavigateToUserDetails = onNavigateToUserDetails,
            onBack = navigator::back
        )
    }
}
