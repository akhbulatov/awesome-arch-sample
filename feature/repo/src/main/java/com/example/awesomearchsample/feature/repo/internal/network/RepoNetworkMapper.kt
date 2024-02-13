package com.example.awesomearchsample.feature.repo.internal.network

import com.example.awesomearchsample.domain.repo.model.Repo
import javax.inject.Inject

internal class RepoNetworkMapper @Inject constructor() {

    fun mapRepoFromNet(netModel: RepoNetModel): Repo {
        return Repo(
            id = netModel.id,
            name = netModel.name,
            author = netModel.owner.login,
            description = netModel.description
        )
    }
}