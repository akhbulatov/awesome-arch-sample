package com.example.awesomearchsample.di

import com.example.awesomearchsample.LaunchNavigatorImpl
import com.example.awesomearchsample.MainNavigatorImpl
import com.example.awesomearchsample.RepoNavigatorImpl
import com.example.awesomearchsample.SearchNavigatorImpl
import com.example.awesomearchsample.feature.launch.navigation.LaunchNavigator
import com.example.awesomearchsample.feature.main.navigation.MainNavigator
import com.example.awesomearchsample.feature.repo.navigation.RepoNavigator
import com.example.awesomearchsample.feature.search.navigation.SearchNavigator

class NavigationFactory {

    val launchNavigator: LaunchNavigator by lazy {
        LaunchNavigatorImpl(
            mainNavigator = mainNavigator
        )
    }
    val mainNavigator: MainNavigator by lazy {
        MainNavigatorImpl()
    }
    val repoNavigator: RepoNavigator by lazy {
        RepoNavigatorImpl()
    }
    val searchNavigator: SearchNavigator by lazy {
        SearchNavigatorImpl()
    }
}