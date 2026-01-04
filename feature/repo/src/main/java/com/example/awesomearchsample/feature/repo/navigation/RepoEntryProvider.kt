package com.example.awesomearchsample.feature.repo.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.feature.repo.repodetails.RepoDetailsRoute
import com.example.awesomearchsample.feature.repo.repodetails.RepoDetailsScreen
import com.example.awesomearchsample.feature.repo.repos.ReposRoute
import com.example.awesomearchsample.feature.repo.repos.ReposScreen

fun EntryProviderScope<NavKey>.addRepoEntries(
    navigate: (NavKey) -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToUserDetails: (String) -> Unit,
    onBack: () -> Unit
) {
    entry<ReposRoute> {
        ReposScreen(
            onNavigateToSearch = onNavigateToSearch,
            onNavigateToRepoDetails = { repoId ->
                navigate(RepoDetailsRoute(repoId = repoId))
            }
        )
    }
    entry<RepoDetailsRoute> { route ->
        RepoDetailsScreen(
            route = route,
            onNavigateToUserDetails = onNavigateToUserDetails,
            onBack = onBack
        )
    }
}
