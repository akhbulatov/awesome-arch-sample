package com.example.awesomearchsample.feature.user.api.navigation

import me.aartikov.alligator.Screen
import java.io.Serializable

object UserScreens {
    data class UserDetails(
        val login: String
    ) : Screen, Serializable
}