package com.example.awesomearchsample.feature.repo.internal.network

import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails

internal fun RepoNetModel.mapRepoFromNet(): Repo {
    return Repo(
        id = id,
        name = name,
        author = owner.login,
        description = description
    )
}

internal fun RepoDetailsNetModel.mapRepoDetailsFromNet(): RepoDetails {
    return RepoDetails(
        id = id,
        name = name,
        author = owner.login,
        description = description,
        starsCount = stargazersCount,
        forksCount = forksCount
    )
}