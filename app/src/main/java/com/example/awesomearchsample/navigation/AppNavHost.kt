package com.example.awesomearchsample.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.awesomearchsample.core.ui.navigation.HostEntryProvider
import com.example.awesomearchsample.feature.launch.LaunchRoute
import com.example.awesomearchsample.feature.launch.host.LaunchHostRoute
import com.example.awesomearchsample.feature.launch.host.LaunchHostScreen
import com.example.awesomearchsample.feature.launch.navigation.addLaunchEntries
import com.example.awesomearchsample.feature.main.host.MainHostRoute
import com.example.awesomearchsample.feature.main.host.MainHostScreen
import com.example.awesomearchsample.feature.main.navigation.navigateToMainHost
import com.example.awesomearchsample.feature.repo.navigation.addRepoEntries
import com.example.awesomearchsample.feature.repo.navigation.navigateToRepoDetails
import com.example.awesomearchsample.feature.repo.repos.ReposRoute
import com.example.awesomearchsample.feature.search.navigation.addSearchEntries
import com.example.awesomearchsample.feature.search.navigation.navigateToSearch
import com.example.awesomearchsample.feature.user.navigation.addUserEntries
import com.example.awesomearchsample.feature.user.navigation.navigateToUserDetails

@Composable
fun AppNavHost() {
    val rootBackStack = rememberNavBackStack(LaunchHostRoute)
    NavDisplay(
        backStack = rootBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        onBack = {
            if (rootBackStack.size > 1) {
                rootBackStack.removeAt(rootBackStack.lastIndex)
            }
        },
        entryProvider = entryProvider {
            entry<LaunchHostRoute> {
                LaunchHostScreen(
                    startDestination = LaunchRoute,
                    entryProvider = launchHostEntryProvider(
                        onNavigateToMainHost = rootBackStack::navigateToMainHost
                    )
                )
            }
            entry<MainHostRoute> {
                MainHostScreen(
                    startDestination = ReposRoute,
                    entryProvider = mainHostEntryProvider()
                )
            }
        }
    )
}

private fun launchHostEntryProvider(
    onNavigateToMainHost: () -> Unit
): HostEntryProvider {
    return { _, _ ->
        entryProvider {
            addLaunchEntries(onNavigateToMainHost = onNavigateToMainHost)
        }
    }
}

private fun mainHostEntryProvider(): HostEntryProvider {
    return { navigate, onBack ->
        entryProvider {
            addRepoEntries(
                navigate = navigate,
                onNavigateToSearch = navigate.navigateToSearch(),
                onNavigateToUserDetails = navigate.navigateToUserDetails(),
                onBack = onBack
            )
            addSearchEntries(
                onNavigateToRepoDetails = navigate.navigateToRepoDetails(),
                onBack = onBack
            )
            addUserEntries(onBack = onBack)
        }
    }
}
