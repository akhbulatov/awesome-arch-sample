package com.example.awesomearchsample.feature.repo.data.network

import com.example.awesomearchsample.feature.repo.domain.model.Repo
import javax.inject.Inject

class RepoNetworkMapper @Inject constructor() {

    fun mapRepoFromNet(netModel: RepoNetModel): Repo {
        return Repo(
            id = netModel.id,
            name = netModel.name,
            author = netModel.owner.login,
            description = netModel.description
        )
    }
}