package com.example.awesomearchsample.feature.repo.data.network

import com.example.awesomearchsample.domain.model.Repo
import javax.inject.Inject

class RepoNetworkMapper @Inject constructor() {

    fun mapRepoFromNet(netModel: RepoNetModel): com.example.awesomearchsample.domain.model.Repo {
        return com.example.awesomearchsample.domain.model.Repo(
            id = netModel.id,
            name = netModel.name,
            author = netModel.owner.login,
            description = netModel.description
        )
    }
}