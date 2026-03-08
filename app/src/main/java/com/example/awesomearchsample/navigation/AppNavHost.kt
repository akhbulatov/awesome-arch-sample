package com.example.awesomearchsample.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.awesomearchsample.core.ui.navigation.backStackAppNavigator
import com.example.awesomearchsample.core.ui.navigation.HostEntryProvider
import com.example.awesomearchsample.feature.launch.LaunchRoute
import com.example.awesomearchsample.feature.launch.di.LaunchFeatureDependencies
import com.example.awesomearchsample.feature.launch.host.LaunchHostRoute
import com.example.awesomearchsample.feature.launch.host.LaunchHostScreen
import com.example.awesomearchsample.feature.launch.navigation.addLaunchEntries
import com.example.awesomearchsample.feature.main.host.MainHostRoute
import com.example.awesomearchsample.feature.main.host.MainHostScreen
import com.example.awesomearchsample.feature.main.navigation.navigateToMainHost
import com.example.awesomearchsample.feature.repo.di.RepoFeatureDependencies
import com.example.awesomearchsample.feature.repo.navigation.addRepoEntries
import com.example.awesomearchsample.feature.repo.navigation.navigateToRepoDetails
import com.example.awesomearchsample.feature.repo.repos.ReposRoute
import com.example.awesomearchsample.feature.search.di.SearchFeatureDependencies
import com.example.awesomearchsample.feature.search.navigation.addSearchEntries
import com.example.awesomearchsample.feature.search.navigation.navigateToSearch
import com.example.awesomearchsample.feature.settings.di.SettingsFeatureDependencies
import com.example.awesomearchsample.feature.settings.navigation.addSettingsEntries
import com.example.awesomearchsample.feature.settings.navigation.navigateToSettings
import com.example.awesomearchsample.feature.user.di.UserFeatureDependencies
import com.example.awesomearchsample.feature.user.navigation.addUserEntries
import com.example.awesomearchsample.feature.user.navigation.navigateToUserDetails

@Composable
fun AppNavHost(
    launchFeatureDependencies: LaunchFeatureDependencies,
    repoFeatureDependencies: RepoFeatureDependencies,
    searchFeatureDependencies: SearchFeatureDependencies,
    settingsFeatureDependencies: SettingsFeatureDependencies,
    userFeatureDependencies: UserFeatureDependencies
) {
    val rootBackStack = rememberNavBackStack(LaunchHostRoute)
    val rootNavigator = backStackAppNavigator(rootBackStack)
    NavDisplay(
        backStack = rootBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        onBack = rootNavigator::back,
        entryProvider = entryProvider {
            entry<LaunchHostRoute> {
                LaunchHostScreen(
                    startDestination = LaunchRoute,
                    entryProvider = launchHostEntryProvider(
                        launchFeatureDependencies = launchFeatureDependencies,
                        onNavigateToMainHost = rootNavigator::navigateToMainHost
                    )
                )
            }
            entry<MainHostRoute> {
                MainHostScreen(
                    startDestination = ReposRoute,
                    entryProvider = mainHostEntryProvider(
                        repoFeatureDependencies = repoFeatureDependencies,
                        searchFeatureDependencies = searchFeatureDependencies,
                        settingsFeatureDependencies = settingsFeatureDependencies,
                        userFeatureDependencies = userFeatureDependencies
                    )
                )
            }
        }
    )
}

private fun launchHostEntryProvider(
    launchFeatureDependencies: LaunchFeatureDependencies,
    onNavigateToMainHost: () -> Unit
): HostEntryProvider {
    return { _ ->
        entryProvider {
            addLaunchEntries(
                launchFeatureDependencies = launchFeatureDependencies,
                onNavigateToMainHost = onNavigateToMainHost
            )
        }
    }
}

private fun mainHostEntryProvider(
    repoFeatureDependencies: RepoFeatureDependencies,
    searchFeatureDependencies: SearchFeatureDependencies,
    settingsFeatureDependencies: SettingsFeatureDependencies,
    userFeatureDependencies: UserFeatureDependencies
): HostEntryProvider {
    return { navigator ->
        entryProvider {
            addRepoEntries(
                repoFeatureDependencies = repoFeatureDependencies,
                navigator = navigator,
                onNavigateToSearch = navigator::navigateToSearch,
                onNavigateToSettings = navigator::navigateToSettings,
                onNavigateToUserDetails = navigator::navigateToUserDetails
            )
            addSearchEntries(
                searchFeatureDependencies = searchFeatureDependencies,
                navigator = navigator,
                onNavigateToRepoDetails = navigator::navigateToRepoDetails
            )
            addSettingsEntries(
                settingsFeatureDependencies = settingsFeatureDependencies,
                navigator = navigator
            )
            addUserEntries(
                userFeatureDependencies = userFeatureDependencies,
                navigator = navigator
            )
        }
    }
}
