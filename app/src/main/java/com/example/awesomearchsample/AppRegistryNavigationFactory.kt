package com.example.awesomearchsample

import com.example.awesomearchsample.feature.main.presentation.MainFlowFragment
import com.example.awesomearchsample.feature.repo.presentation.repos.ReposFragment
import me.aartikov.alligator.navigationfactories.RegistryNavigationFactory

class AppRegistryNavigationFactory : RegistryNavigationFactory() {

    init {
        registerFragment(Screens.MainFlow::class.java, MainFlowFragment::class.java)
        registerFragment(Screens.Repos::class.java, ReposFragment::class.java)
    }
}
