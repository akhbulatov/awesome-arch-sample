package com.example.awesomearchsample.data.search.local.database.model

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(
    tableName = "search_query"
)
internal data class SearchQueryDbModel(
    @PrimaryKey @ColumnInfo("value") val value: String
)
