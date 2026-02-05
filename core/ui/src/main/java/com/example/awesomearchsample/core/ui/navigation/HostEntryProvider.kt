package com.example.awesomearchsample.core.ui.navigation

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey

typealias HostEntryProvider = (
    navigator: AppNavigator
) -> (NavKey) -> NavEntry<NavKey>
