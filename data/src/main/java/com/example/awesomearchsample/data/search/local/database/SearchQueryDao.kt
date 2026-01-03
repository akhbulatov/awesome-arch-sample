package com.example.awesomearchsample.data.search.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.awesomearchsample.data.search.local.database.model.SearchQueryDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchQueryDao {
    @Query("SELECT * FROM search_query")
    fun getAll(): Flow<List<SearchQueryDbModel>>

    @Insert
    suspend fun insert(query: SearchQueryDbModel)
}
