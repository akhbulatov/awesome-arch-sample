package com.example.awesomearchsample

import cafe.adriel.voyager.core.screen.Screen
import com.example.awesomearchsample.feature.launch.navigation.LaunchMediator
import com.example.awesomearchsample.feature.main.host.MainHostScreen
import com.example.awesomearchsample.feature.main.navigation.MainMediator
import com.example.awesomearchsample.feature.repo.api.navigation.RepoMediator
import com.example.awesomearchsample.feature.repo.api.repos.ReposScreen
import com.example.awesomearchsample.feature.user.api.userdetails.UserDetailsScreen
import javax.inject.Inject

class LaunchMediatorImpl @Inject constructor(
    private val mainMediator: MainMediator
) : LaunchMediator {

    override fun getMainHostScreen(): Screen =
        MainHostScreen(startScreen = mainMediator.getReposScreen())
}

class MainMediatorImpl @Inject constructor() : MainMediator {
    override fun getReposScreen(): Screen = ReposScreen
}

class RepoMediatorImpl @Inject constructor() : RepoMediator {
    override fun getUserDetailsScreen(login: String): Screen =
        UserDetailsScreen(login = login)
}