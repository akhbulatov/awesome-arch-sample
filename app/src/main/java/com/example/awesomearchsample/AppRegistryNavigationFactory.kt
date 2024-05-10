package com.example.awesomearchsample

import com.example.awesomearchsample.feature.launch.LaunchFragment
import com.example.awesomearchsample.feature.launch.flow.LaunchFlowFragment
import com.example.awesomearchsample.feature.launch.navigation.LaunchScreens
import com.example.awesomearchsample.feature.main.flow.MainFlowFragment
import com.example.awesomearchsample.feature.main.navigation.MainScreens
import com.example.awesomearchsample.feature.repo.api.navigation.RepoScreens
import com.example.awesomearchsample.feature.repo.api.repodetails.RepoDetailsFragment
import com.example.awesomearchsample.feature.repo.api.repos.ReposFragment
import com.example.awesomearchsample.feature.user.api.navigation.UserScreens
import com.example.awesomearchsample.feature.user.api.userdetails.UserDetailsFragment
import me.aartikov.alligator.navigationfactories.RegistryNavigationFactory

class AppRegistryNavigationFactory : RegistryNavigationFactory() {

    init {
        // launch
        registerFragment(LaunchScreens.LaunchFlow::class.java, LaunchFlowFragment::class.java)
        registerFragment(LaunchScreens.Launch::class.java, LaunchFragment::class.java)
        // main
        registerFragment(MainScreens.MainFlow::class.java, MainFlowFragment::class.java)
        // repo
        registerFragment(RepoScreens.Repos::class.java, ReposFragment::class.java)
        registerFragment(RepoScreens.RepoDetails::class.java, RepoDetailsFragment::class.java)
        // user
        registerFragment(UserScreens.UserDetails::class.java, UserDetailsFragment::class.java)
    }
}
