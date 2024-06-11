package com.example.awesomearchsample.core.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_preferences")

@Singleton
class AppPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun isFirstLaunch(): Flow<Boolean> =
        context.dataStore.data.map { prefs ->
            prefs[IS_FIRST_LAUNCH] ?: false
        }

    suspend fun setIsFirstLaunch(value: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_FIRST_LAUNCH] = value
        }
    }

    companion object {
        private val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
    }
}