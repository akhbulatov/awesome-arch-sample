package com.example.awesomearchsample.feature.launch.host

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.awesomearchsample.core.ui.navigation.backStackAppNavigator
import com.example.awesomearchsample.core.ui.navigation.HostEntryProvider

@Composable
fun LaunchHostScreen(
    startDestination: NavKey,
    entryProvider: HostEntryProvider
) {
    LaunchHostContent(
        startDestination = startDestination,
        entryProvider = entryProvider
    )
}

@Composable
private fun LaunchHostContent(
    startDestination: NavKey,
    entryProvider: HostEntryProvider
) {
    val backStack = rememberNavBackStack(startDestination)
    val navigator = backStackAppNavigator(backStack)
    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        onBack = navigator::back,
        entryProvider = entryProvider(navigator)
    )
}
