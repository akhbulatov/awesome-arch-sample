package com.example.awesomearchsample.feature.search.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.feature.search.SearchRoute
import com.example.awesomearchsample.feature.search.SearchScreen

fun EntryProviderScope<NavKey>.addSearchEntries(
    onNavigateToRepoDetails: (Long) -> Unit,
    onBack: () -> Unit
) {
    entry<SearchRoute> {
        SearchScreen(
            onNavigateToRepoDetails = onNavigateToRepoDetails,
            onBack = onBack
        )
    }
}
