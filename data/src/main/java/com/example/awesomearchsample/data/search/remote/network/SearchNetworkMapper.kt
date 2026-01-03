package com.example.awesomearchsample.data.search.remote.network

import com.example.awesomearchsample.data.repo.remote.network.mapRepoFromNet
import com.example.awesomearchsample.data.repo.remote.network.model.RepoNetModel
import com.example.awesomearchsample.data.search.remote.network.model.SearchResultNetModel
import com.example.awesomearchsample.domain.search.model.SearchResult

internal fun SearchResultNetModel<List<RepoNetModel>>.mapReposToDomainModel(): SearchResult.Repos {
    return SearchResult.Repos(
        data = this.items.map { repo ->
            repo.mapRepoFromNet()
        }
    )
}
