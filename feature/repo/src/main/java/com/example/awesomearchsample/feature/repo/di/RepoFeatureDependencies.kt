package com.example.awesomearchsample.feature.repo.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.awesomearchsample.core.ui.util.getApplicationInstance
import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsScreenDependencies
import com.example.awesomearchsample.feature.repo.repos.di.ReposScreenDependencies

interface RepoFeatureDependencies {
    val reposScreenDependencies: ReposScreenDependencies
    val repoDetailsScreenDependencies: RepoDetailsScreenDependencies
}

interface RepoFeatureDependenciesProvider {
    fun getRepoFeatureDependencies(): RepoFeatureDependencies
}

@Composable
internal fun rememberRepoFeatureDependencies(): RepoFeatureDependencies {
    val application = getApplicationInstance()
    return remember { (application as RepoFeatureDependenciesProvider).getRepoFeatureDependencies() }
}
