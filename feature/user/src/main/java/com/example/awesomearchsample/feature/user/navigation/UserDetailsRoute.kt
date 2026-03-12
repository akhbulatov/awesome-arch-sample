package com.example.awesomearchsample.feature.user.navigation

import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.core.ui.navigation.NavRoute
import kotlinx.serialization.Serializable

@Serializable
internal data class UserDetailsRoute(val login: String) : NavRoute

fun AppNavigator.navigateToUserDetails(login: String) {
    navigateTo(UserDetailsRoute(login = login))
}
