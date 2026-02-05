package com.example.awesomearchsample.feature.user.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.user.userdetails.UserDetailsRoute
import com.example.awesomearchsample.feature.user.userdetails.UserDetailsScreen

fun EntryProviderScope<NavKey>.addUserEntries(
    navigator: AppNavigator
) {
    entry<UserDetailsRoute> { route ->
        UserDetailsScreen(
            route = route,
            onBack = navigator::back
        )
    }
}
