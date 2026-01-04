package com.example.awesomearchsample.core.ui.navigation

import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey

typealias HostEntryProvider = (
    navigate: (NavKey) -> Unit,
    onBack: () -> Unit
) -> (NavKey) -> NavEntry<NavKey>
