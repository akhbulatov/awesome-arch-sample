package com.example.awesomearchsample.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.awesomearchsample.data.search.database.SearchQueryDao
import com.example.awesomearchsample.data.search.database.model.SearchQueryDbModel

@Database(
    entities = [
        SearchQueryDbModel::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchQueryDao(): SearchQueryDao
}