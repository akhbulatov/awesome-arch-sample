package com.example.awesomearchsample.data.search.local.database

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import com.example.awesomearchsample.data.search.local.database.model.SearchQueryDbModel
import kotlinx.coroutines.flow.Flow

@Dao
internal interface SearchQueryDao {
    @Query("SELECT * FROM search_query")
    fun getAll(): Flow<List<SearchQueryDbModel>>

    @Insert
    suspend fun insert(query: SearchQueryDbModel)
}
