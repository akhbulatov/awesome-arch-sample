package com.example.awesomearchsample.data.repo.remote.network

import com.example.awesomearchsample.data.repo.remote.network.model.RepoDetailsNetModel
import com.example.awesomearchsample.data.repo.remote.network.model.RepoNetModel
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails

internal fun RepoNetModel.toRepoDomain(): Repo {
    return Repo(
        id = id,
        name = name,
        author = owner.login,
        description = description
    )
}

internal fun RepoDetailsNetModel.toRepoDetailsDomain(): RepoDetails {
    return RepoDetails(
        id = id,
        name = name,
        author = owner.login,
        description = description,
        starsCount = stargazersCount,
        forksCount = forksCount
    )
}
