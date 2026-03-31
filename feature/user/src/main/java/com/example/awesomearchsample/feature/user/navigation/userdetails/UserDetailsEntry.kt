package com.example.awesomearchsample.feature.user.navigation.userdetails

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.user.di.UserFeatureDependencies
import com.example.awesomearchsample.feature.user.userdetails.UserDetailsScreen

internal fun EntryProviderScope<NavKey>.addUserDetailsEntry(
    userFeatureDependencies: UserFeatureDependencies,
    navigator: AppNavigator
) {
    entry<UserDetailsRoute> { route ->
        UserDetailsScreen(
            route = route,
            dependencies = userFeatureDependencies.userDetailsScreenDependencies,
            onBack = navigator::back
        )
    }
}
