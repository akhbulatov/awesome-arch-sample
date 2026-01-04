package com.example.awesomearchsample.feature.main.host

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay

@Composable
fun MainHostScreen(
    startDestination: NavKey,
    entryProvider: (navigate: (NavKey) -> Unit, onBack: () -> Unit) -> (NavKey) -> NavEntry<NavKey>
) {
    MainHostContent(
        startDestination = startDestination,
        entryProvider = entryProvider
    )
}

@Composable
private fun MainHostContent(
    startDestination: NavKey,
    entryProvider: (navigate: (NavKey) -> Unit, onBack: () -> Unit) -> (NavKey) -> NavEntry<NavKey>
) {
    val mainBackStack = rememberNavBackStack(startDestination)
    val onBack = {
        if (mainBackStack.size > 1) {
            mainBackStack.removeAt(mainBackStack.lastIndex)
        }
    }
    NavDisplay(
        backStack = mainBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        onBack = onBack,
        entryProvider = entryProvider(
            { destination -> mainBackStack.add(destination) },
            onBack
        )
    )
}
