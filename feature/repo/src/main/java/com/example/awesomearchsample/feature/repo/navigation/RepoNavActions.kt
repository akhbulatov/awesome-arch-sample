package com.example.awesomearchsample.feature.repo.navigation

import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.feature.repo.repodetails.RepoDetailsRoute

fun ((NavKey) -> Unit).navigateToRepoDetails(): (Long) -> Unit = { repoId ->
    this(RepoDetailsRoute(repoId = repoId))
}
