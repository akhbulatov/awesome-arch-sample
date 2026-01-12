package com.example.awesomearchsample.data.search.local.database

import com.example.awesomearchsample.data.search.local.database.model.SearchQueryDbModel
import com.example.awesomearchsample.domain.search.model.SearchQuery

internal fun SearchQuery.toSearchQueryDbModel() = SearchQueryDbModel(
    value = value
)

internal fun SearchQueryDbModel.toSearchQueryDomain() = SearchQuery(
    value = value
)
