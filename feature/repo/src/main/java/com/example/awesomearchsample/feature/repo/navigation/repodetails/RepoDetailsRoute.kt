package com.example.awesomearchsample.feature.repo.navigation.repodetails

import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.core.ui.navigation.NavRoute
import kotlinx.serialization.Serializable

@Serializable
internal data class RepoDetailsRoute(val repoId: Long) : NavRoute

fun AppNavigator.navigateToRepoDetails(repoId: Long) {
    navigateTo(RepoDetailsRoute(repoId = repoId))
}
