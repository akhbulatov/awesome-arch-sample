package com.example.awesomearchsample.data.search.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "search_query"
)
data class SearchQueryDbModel(
    @PrimaryKey @ColumnInfo("value") val value: String
)
