package com.example.awesomearchsample.domain.search.model

import com.example.awesomearchsample.domain.repo.model.Repo

sealed class SearchResult {
    data class Repos(val data: List<Repo>) : SearchResult()
}