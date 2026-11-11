package com.example.awesomearchsample.data.di

import android.content.Context
import androidx.room3.Room
import com.example.awesomearchsample.core.preferences.AppPreferences
import com.example.awesomearchsample.data.AppDatabase
import com.example.awesomearchsample.data.appsettings.di.AppSettingsDataFactory
import com.example.awesomearchsample.data.repo.di.RepoDataFactory
import com.example.awesomearchsample.data.search.di.SearchDataFactory
import com.example.awesomearchsample.data.user.di.UserDataFactory
import io.ktor.client.HttpClient

class DataFactory(
    appContext: Context,
    httpClientProvider: () -> HttpClient,
    appPreferencesProvider: () -> AppPreferences
) {

    private val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(
            context = appContext,
            klass = AppDatabase::class.java,
            name = "awesomearchsample.db"
        )
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }

    val repoDataFactory: RepoDataFactory by lazy {
        RepoDataFactory(
            httpClientProvider = httpClientProvider
        )
    }
    val searchDataFactory: SearchDataFactory by lazy {
        SearchDataFactory(
            appDatabase = appDatabase,
            httpClientProvider = httpClientProvider
        )
    }
    val userDataFactory: UserDataFactory by lazy {
        UserDataFactory(
            httpClientProvider = httpClientProvider
        )
    }
    val appSettingsDataFactory: AppSettingsDataFactory by lazy {
        AppSettingsDataFactory(
            appPreferencesProvider = appPreferencesProvider
        )
    }
}
