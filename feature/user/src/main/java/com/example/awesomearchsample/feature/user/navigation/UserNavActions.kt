package com.example.awesomearchsample.feature.user.navigation

import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.user.userdetails.UserDetailsRoute

fun AppNavigator.navigateToUserDetails(login: String) {
    navigateTo(UserDetailsRoute(login = login))
}
