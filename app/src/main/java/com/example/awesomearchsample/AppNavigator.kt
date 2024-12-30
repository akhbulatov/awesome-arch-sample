package com.example.awesomearchsample

import cafe.adriel.voyager.core.screen.Screen
import com.example.awesomearchsample.feature.launch.navigation.LaunchNavigator
import com.example.awesomearchsample.feature.main.host.MainHostScreen
import com.example.awesomearchsample.feature.main.navigation.MainNavigator
import com.example.awesomearchsample.feature.repo.navigation.RepoNavigator
import com.example.awesomearchsample.feature.repo.repodetails.RepoDetailsScreen
import com.example.awesomearchsample.feature.repo.repos.ReposScreen
import com.example.awesomearchsample.feature.search.SearchScreen
import com.example.awesomearchsample.feature.search.navigation.SearchNavigator
import com.example.awesomearchsample.feature.user.userdetails.UserDetailsScreen

// launch
class LaunchNavigatorImpl(
    private val mainNavigator: MainNavigator
) : LaunchNavigator {

    override fun getMainHostScreen(): Screen =
        MainHostScreen(startScreen = mainNavigator.getReposScreen())
}

// main
class MainNavigatorImpl : MainNavigator {
    override fun getReposScreen(): Screen = ReposScreen
}

// repo
class RepoNavigatorImpl : RepoNavigator {
    override fun getUserDetailsScreen(login: String): Screen =
        UserDetailsScreen(login = login)

    override fun getSearchScreen(): Screen = SearchScreen
}

// search
class SearchNavigatorImpl : SearchNavigator {
    override fun getRepoDetailsScreen(repoId: Long): Screen =
        RepoDetailsScreen(repoId = repoId)
}