package com.example.awesomearchsample.feature.repo.navigation

import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.feature.repo.repodetails.RepoDetailsRoute

fun AppNavigator.navigateToRepoDetails(repoId: Long) {
    navigateTo(RepoDetailsRoute(repoId = repoId))
}
