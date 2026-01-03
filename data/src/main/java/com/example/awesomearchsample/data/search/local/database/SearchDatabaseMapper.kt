package com.example.awesomearchsample.data.search.local.database

import com.example.awesomearchsample.data.search.local.database.model.SearchQueryDbModel
import com.example.awesomearchsample.domain.search.model.SearchQuery

internal fun SearchQuery.toDbModel() = SearchQueryDbModel(
    value = value
)

internal fun SearchQueryDbModel.toDomainModel() = SearchQuery(
    value = value
)
