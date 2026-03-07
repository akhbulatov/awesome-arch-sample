package com.example.awesomearchsample.data.di

import androidx.room.Room
import com.example.awesomearchsample.core.network.di.NetworkFactory
import com.example.awesomearchsample.core.preferences.di.PreferencesFactory
import com.example.awesomearchsample.data.AppDatabase
import com.example.awesomearchsample.data.appconfig.di.AppConfigDataFactory
import com.example.awesomearchsample.data.repo.di.RepoDataFactory
import com.example.awesomearchsample.data.search.di.SearchDataFactory
import com.example.awesomearchsample.data.user.di.UserDataFactory

class DataFactory(
    appContextProvider: AppContextProvider,
    networkFactory: NetworkFactory,
    preferencesFactory: PreferencesFactory
) {

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(
            context = appContextProvider.appContext,
            klass = AppDatabase::class.java,
            name = "awesomearchsample.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    val repoDataFactory: RepoDataFactory by lazy {
        RepoDataFactory(
            networkFactory = networkFactory
        )
    }
    val searchDataFactory: SearchDataFactory by lazy {
        SearchDataFactory(
            appDatabase = appDatabase,
            networkFactory = networkFactory
        )
    }
    val userDataFactory: UserDataFactory by lazy {
        UserDataFactory(
            networkFactory = networkFactory
        )
    }
    val appConfigDataFactory: AppConfigDataFactory by lazy {
        AppConfigDataFactory(
            preferencesFactory = preferencesFactory
        )
    }
}
