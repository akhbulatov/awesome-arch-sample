package com.example.awesomearchsample.data.search.network

import com.example.awesomearchsample.data.repo.network.mapRepoFromNet
import com.example.awesomearchsample.data.repo.network.model.RepoNetModel
import com.example.awesomearchsample.data.search.network.model.SearchResultNetModel
import com.example.awesomearchsample.domain.search.model.SearchResult

internal fun SearchResultNetModel<List<RepoNetModel>>.mapReposToDomainModel(): SearchResult.Repos {
    return SearchResult.Repos(
        data = this.items.map { repo ->
            repo.mapRepoFromNet()
        }
    )
}