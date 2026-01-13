package com.example.awesomearchsample.feature.repo.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.awesomearchsample.core.ui.util.getApplicationInstance
import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsDependencies
import com.example.awesomearchsample.feature.repo.repos.di.ReposDependencies

interface RepoFeatureDependencies {
    val reposDependencies: ReposDependencies
    val repoDetailsDependencies: RepoDetailsDependencies
}

interface RepoFeatureDependenciesProvider {
    fun getRepoFeatureDependencies(): RepoFeatureDependencies
}

@Composable
internal fun rememberRepoFeatureDependencies(): RepoFeatureDependencies {
    val application = getApplicationInstance()
    return remember { (application as RepoFeatureDependenciesProvider).getRepoFeatureDependencies() }
}
