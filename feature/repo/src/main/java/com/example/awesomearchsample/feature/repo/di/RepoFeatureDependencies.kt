package com.example.awesomearchsample.feature.repo.di

import com.example.awesomearchsample.feature.repo.repodetails.di.RepoDetailsScreenDependencies
import com.example.awesomearchsample.feature.repo.repos.di.ReposScreenDependencies

interface RepoFeatureDependencies {
    val reposScreenDependencies: ReposScreenDependencies
    val repoDetailsScreenDependencies: RepoDetailsScreenDependencies
}
