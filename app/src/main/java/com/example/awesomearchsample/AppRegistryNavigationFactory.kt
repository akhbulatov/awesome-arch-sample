package com.example.awesomearchsample

import com.example.awesomearchsample.feature.repo.api.navigation.RepoScreens
import com.example.awesomearchsample.feature.repo.api.repodetails.RepoDetailsFragment
import com.example.awesomearchsample.feature.user.api.navigation.UserScreens
import com.example.awesomearchsample.feature.user.api.userdetails.UserDetailsFragment
import me.aartikov.alligator.navigationfactories.RegistryNavigationFactory

class AppRegistryNavigationFactory : RegistryNavigationFactory() {

    init {
        // repo
        registerFragment(RepoScreens.RepoDetails::class.java, RepoDetailsFragment::class.java)
        // user
        registerFragment(UserScreens.UserDetails::class.java, UserDetailsFragment::class.java)
    }
}
