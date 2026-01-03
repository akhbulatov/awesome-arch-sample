package com.example.awesomearchsample.data.search.local.database

import com.example.awesomearchsample.data.search.local.database.model.SearchQueryDbModel
import com.example.awesomearchsample.domain.search.model.SearchQuery

internal fun SearchQuery.mapSearchQueryToDbModel() = SearchQueryDbModel(
    value = value
)

internal fun SearchQueryDbModel.mapSearchQueryFromDbModel() = SearchQuery(
    value = value
)
