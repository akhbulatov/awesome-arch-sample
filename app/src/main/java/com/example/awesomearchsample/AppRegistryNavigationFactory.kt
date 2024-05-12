package com.example.awesomearchsample

import com.example.awesomearchsample.feature.user.api.navigation.UserScreens
import com.example.awesomearchsample.feature.user.api.userdetails.UserDetailsFragment
import me.aartikov.alligator.navigationfactories.RegistryNavigationFactory

class AppRegistryNavigationFactory : RegistryNavigationFactory() {

    init {
        // user
        registerFragment(UserScreens.UserDetails::class.java, UserDetailsFragment::class.java)
    }
}
