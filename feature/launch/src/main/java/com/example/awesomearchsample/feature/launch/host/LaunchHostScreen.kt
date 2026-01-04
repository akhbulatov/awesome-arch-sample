package com.example.awesomearchsample.feature.launch.host

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay

@Composable
fun LaunchHostScreen(
    startDestination: NavKey,
    entryProvider: (navigate: (NavKey) -> Unit, onBack: () -> Unit) -> (NavKey) -> NavEntry<NavKey>
) {
    LaunchHostContent(
        startDestination = startDestination,
        entryProvider = entryProvider
    )
}

@Composable
private fun LaunchHostContent(
    startDestination: NavKey,
    entryProvider: (navigate: (NavKey) -> Unit, onBack: () -> Unit) -> (NavKey) -> NavEntry<NavKey>
) {
    val backStack = rememberNavBackStack(startDestination)
    val onBack = {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
        }
    }
    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        onBack = onBack,
        entryProvider = entryProvider(
            { destination -> backStack.add(destination) },
            onBack
        )
    )
}
