package com.example.awesomearchsample.feature.search.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.search.SearchScreen
import com.example.awesomearchsample.feature.search.di.SearchFeatureDependencies

fun EntryProviderScope<NavKey>.addSearchEntries(
    searchFeatureDependencies: SearchFeatureDependencies,
    navigator: AppNavigator,
    onNavigateToRepoDetails: (Long) -> Unit
) {
    entry<SearchRoute> {
        SearchScreen(
            dependencies = searchFeatureDependencies.searchScreenDependencies,
            onNavigateToRepoDetails = onNavigateToRepoDetails,
            onBack = navigator::back
        )
    }
}
