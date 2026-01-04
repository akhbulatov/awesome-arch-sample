package com.example.awesomearchsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.awesomearchsample.core.ui.designsystem.AppTheme
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.awesomearchsample.feature.launch.LaunchRoute
import com.example.awesomearchsample.feature.launch.LaunchScreen
import com.example.awesomearchsample.feature.main.host.MainHostRoute
import com.example.awesomearchsample.feature.repo.repodetails.RepoDetailsRoute
import com.example.awesomearchsample.feature.repo.repodetails.RepoDetailsScreen
import com.example.awesomearchsample.feature.repo.repos.ReposRoute
import com.example.awesomearchsample.feature.repo.repos.ReposScreen
import com.example.awesomearchsample.feature.search.SearchRoute
import com.example.awesomearchsample.feature.search.SearchScreen
import com.example.awesomearchsample.feature.user.userdetails.UserDetailsRoute
import com.example.awesomearchsample.feature.user.userdetails.UserDetailsScreen

class AwesomeArchSampleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val rootBackStack = rememberNavBackStack(LaunchRoute)
                NavDisplay(
                    backStack = rootBackStack,
                    entryDecorators = listOf(
                        rememberSaveableStateHolderNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator()
                    ),
                    onBack = {
                        if (rootBackStack.size > 1) {
                            rootBackStack.removeLast()
                        }
                    },
                    entryProvider = entryProvider<NavKey> {
                        entry<LaunchRoute> {
                            LaunchScreen {
                                rootBackStack.clear()
                                rootBackStack.add(MainHostRoute)
                            }
                        }
                        entry<MainHostRoute> {
                            MainHostContent()
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun MainHostContent() {
    val mainBackStack = rememberNavBackStack(ReposRoute)
    NavDisplay(
        backStack = mainBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        onBack = {
            if (mainBackStack.size > 1) {
                mainBackStack.removeLast()
            }
        },
        entryProvider = entryProvider<NavKey> {
            entry<ReposRoute> {
                ReposScreen(
                    onNavigateToSearch = { mainBackStack.add(SearchRoute) },
                    onNavigateToRepoDetails = { repoId ->
                        mainBackStack.add(RepoDetailsRoute(repoId = repoId))
                    }
                )
            }
            entry<SearchRoute> {
                SearchScreen(
                    onNavigateToRepoDetails = { repoId ->
                        mainBackStack.add(RepoDetailsRoute(repoId = repoId))
                    },
                    onBack = {
                        if (mainBackStack.size > 1) {
                            mainBackStack.removeLast()
                        }
                    }
                )
            }
            entry<RepoDetailsRoute> { route ->
                RepoDetailsScreen(
                    route = route,
                    onNavigateToUserDetails = { login ->
                        mainBackStack.add(UserDetailsRoute(login = login))
                    },
                    onBack = {
                        if (mainBackStack.size > 1) {
                            mainBackStack.removeLast()
                        }
                    }
                )
            }
            entry<UserDetailsRoute> { route ->
                UserDetailsScreen(
                    route = route,
                    onBack = {
                        if (mainBackStack.size > 1) {
                            mainBackStack.removeLast()
                        }
                    }
                )
            }
        }
    )
}
