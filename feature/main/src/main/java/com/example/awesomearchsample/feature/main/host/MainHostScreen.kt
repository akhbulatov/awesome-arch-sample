package com.example.awesomearchsample.feature.main.host

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.awesomearchsample.core.ui.navigation.HostEntryProvider

@Composable
fun MainHostScreen(
    startDestination: NavKey,
    entryProvider: HostEntryProvider
) {
    MainHostContent(
        startDestination = startDestination,
        entryProvider = entryProvider
    )
}

@Composable
private fun MainHostContent(
    startDestination: NavKey,
    entryProvider: HostEntryProvider
) {
    val mainBackStack = rememberNavBackStack(startDestination)
    val onBack: () -> Unit = { mainBackStack.removeLastOrNull() }
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
