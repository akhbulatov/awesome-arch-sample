package com.example.awesomearchsample.feature.repo.api.navigation

import me.aartikov.alligator.Screen
import java.io.Serializable

object RepoScreens {
    object Repos : Screen, Serializable

    data class RepoDetails(
        val repoId: Long
    ) : Screen, Serializable
}