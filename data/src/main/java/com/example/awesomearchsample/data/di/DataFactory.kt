package com.example.awesomearchsample.data.di

import androidx.room.Room
import com.example.awesomearchsample.core.corefactory.di.CoreFactory
import com.example.awesomearchsample.data.AppDatabase
import com.example.awesomearchsample.data.apppreferences.di.AppPreferencesDataFactory
import com.example.awesomearchsample.data.repo.di.RepoDataFactory
import com.example.awesomearchsample.data.search.di.SearchDataFactory
import com.example.awesomearchsample.data.user.di.UserDataFactory

class DataFactory(
    coreFactory: CoreFactory
) {

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(
            context = coreFactory.context,
            klass = AppDatabase::class.java,
            name = "awesomearchsample.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    val repoDataFactory: RepoDataFactory by lazy {
        RepoDataFactory(
            coreFactory = coreFactory
        )
    }
    val searchDataFactory: SearchDataFactory by lazy {
        SearchDataFactory(
            appDatabase = appDatabase,
            coreFactory = coreFactory
        )
    }
    val userDataFactory: UserDataFactory by lazy {
        UserDataFactory(
            coreFactory = coreFactory
        )
    }
    val appPreferencesDataFactory: AppPreferencesDataFactory by lazy {
        AppPreferencesDataFactory(
            coreFactory = coreFactory
        )
    }
}