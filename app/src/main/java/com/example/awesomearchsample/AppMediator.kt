package com.example.awesomearchsample

import com.example.awesomearchsample.feature.launch.navigation.LaunchMediator
import com.example.awesomearchsample.feature.main.navigation.MainMediator
import com.example.awesomearchsample.feature.main.navigation.MainScreens
import com.example.awesomearchsample.feature.repo.api.navigation.RepoScreens
import me.aartikov.alligator.Screen
import javax.inject.Inject

class LaunchMediatorImpl @Inject constructor() : LaunchMediator {
    override fun getMainFlowScreen(): Screen = MainScreens.MainFlow
}

class MainMediatorImpl @Inject constructor() : MainMediator {
    override fun getReposScreen(): Screen = RepoScreens.Repos
}