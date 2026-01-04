package com.example.awesomearchsample.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.awesomearchsample.core.ui.navigation.HostEntryProvider
import com.example.awesomearchsample.feature.launch.LaunchRoute
import com.example.awesomearchsample.feature.launch.LaunchScreen
import com.example.awesomearchsample.feature.launch.host.LaunchHostRoute
import com.example.awesomearchsample.feature.launch.host.LaunchHostScreen
import com.example.awesomearchsample.feature.main.host.MainHostRoute
import com.example.awesomearchsample.feature.main.host.MainHostScreen
import com.example.awesomearchsample.feature.repo.repodetails.RepoDetailsRoute
import com.example.awesomearchsample.feature.repo.repodetails.RepoDetailsScreen
import com.example.awesomearchsample.feature.repo.repos.ReposRoute
import com.example.awesomearchsample.feature.repo.repos.ReposScreen
import com.example.awesomearchsample.feature.search.SearchRoute
import com.example.awesomearchsample.feature.search.SearchScreen
import com.example.awesomearchsample.feature.user.userdetails.UserDetailsRoute
import com.example.awesomearchsample.feature.user.userdetails.UserDetailsScreen

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
                    entryProvider = launchHostEntryProvider(rootBackStack)
                )
            }
            entry<MainHostRoute> {
                MainHostScreen(
                    startDestination = ReposRoute,
                    entryProvider = mainHostEntryProvider(rootBackStack)
                )
            }
        }
    )
}

private fun launchHostEntryProvider(
    rootBackStack: NavBackStack<NavKey>
): HostEntryProvider {
    return { _, _ ->
        entryProvider {
            entry<LaunchRoute> {
                LaunchScreen(
                    onNavigateToMainHost = {
                        rootBackStack.clear()
                        rootBackStack.add(MainHostRoute)
                    }
                )
            }
        }
    }
}

private fun mainHostEntryProvider(
    rootBackStack: NavBackStack<NavKey>
): HostEntryProvider {
    return { navigate, onBack ->
        entryProvider {
            entry<ReposRoute> {
                ReposScreen(
                    onNavigateToSearch = { navigate(SearchRoute) },
                    onNavigateToRepoDetails = { repoId ->
                        navigate(RepoDetailsRoute(repoId = repoId))
                    }
                )
            }
            entry<SearchRoute> {
                SearchScreen(
                    onNavigateToRepoDetails = { repoId ->
                        navigate(RepoDetailsRoute(repoId = repoId))
                    },
                    onBack = onBack
                )
            }
            entry<RepoDetailsRoute> { route ->
                RepoDetailsScreen(
                    route = route,
                    onNavigateToUserDetails = { login ->
                        navigate(UserDetailsRoute(login = login))
                    },
                    onBack = onBack
                )
            }
            entry<UserDetailsRoute> { route ->
                UserDetailsScreen(
                    route = route,
                    onBack = onBack
                )
            }
        }
    }
}
