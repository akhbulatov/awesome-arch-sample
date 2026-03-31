package com.example.awesomearchsample.feature.search.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.search.di.SearchFeatureDependencies
import com.example.awesomearchsample.feature.search.navigation.search.addSearchEntry

fun EntryProviderScope<NavKey>.addSearchEntries(
    searchFeatureDependencies: SearchFeatureDependencies,
    navigator: AppNavigator,
    onNavigateToRepoDetails: (Long) -> Unit
) {
    addSearchEntry(
        searchFeatureDependencies = searchFeatureDependencies,
        navigator = navigator,
        onNavigateToRepoDetails = onNavigateToRepoDetails
    )
}
