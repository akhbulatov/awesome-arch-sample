package com.example.awesomearchsample.data

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.awesomearchsample.data.search.local.database.SearchQueryDao
import com.example.awesomearchsample.data.search.local.database.model.SearchQueryDbModel

@Database(
    entities = [
        SearchQueryDbModel::class
    ],
    version = 1,
    exportSchema = true
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun searchQueryDao(): SearchQueryDao
}
