package com.example.awesomearchsample.core.preferences.di

import android.content.Context
import com.example.awesomearchsample.core.preferences.AppPreferences
import com.example.awesomearchsample.core.preferences.DataStoreAppPreferences

class PreferencesFactory(
    private val context: Context
) {

    val appPreferences: AppPreferences by lazy {
        DataStoreAppPreferences(
            context = context
        )
    }
}
