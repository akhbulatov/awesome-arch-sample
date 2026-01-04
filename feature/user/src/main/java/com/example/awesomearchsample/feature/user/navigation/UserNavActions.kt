package com.example.awesomearchsample.feature.user.navigation

import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.feature.user.userdetails.UserDetailsRoute

fun ((NavKey) -> Unit).navigateToUserDetails(login: String) {
    this(UserDetailsRoute(login = login))
}
