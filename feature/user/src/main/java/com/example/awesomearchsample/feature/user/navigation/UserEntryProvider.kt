package com.example.awesomearchsample.feature.user.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.user.di.UserFeatureDependencies
import com.example.awesomearchsample.feature.user.navigation.userdetails.addUserDetailsEntry

fun EntryProviderScope<NavKey>.addUserEntries(
    userFeatureDependencies: UserFeatureDependencies,
    navigator: AppNavigator
) {
    addUserDetailsEntry(
        userFeatureDependencies = userFeatureDependencies,
        navigator = navigator
    )
}
