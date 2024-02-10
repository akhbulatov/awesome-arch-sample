package com.example.awesomearchsample

import com.example.awesomearchsample.feature.main.flow.MainFlowFragment
import com.example.awesomearchsample.feature.main.navigation.MainScreens
import com.example.awesomearchsample.feature.repo.api.navigation.RepoScreens
import com.example.awesomearchsample.feature.repo.api.repos.ReposFragment
import me.aartikov.alligator.navigationfactories.RegistryNavigationFactory

class AppRegistryNavigationFactory : RegistryNavigationFactory() {

    init {
        registerFragment(MainScreens.MainFlow::class.java, MainFlowFragment::class.java)
        registerFragment(RepoScreens.Repos::class.java, ReposFragment::class.java)
    }
}
