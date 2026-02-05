package com.example.awesomearchsample.feature.search.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.search.SearchRoute
import com.example.awesomearchsample.feature.search.SearchScreen

fun EntryProviderScope<NavKey>.addSearchEntries(
    navigator: AppNavigator,
    onNavigateToRepoDetails: (Long) -> Unit
) {
    entry<SearchRoute> {
        SearchScreen(
            onNavigateToRepoDetails = onNavigateToRepoDetails,
            onBack = navigator::back
        )
    }
}
