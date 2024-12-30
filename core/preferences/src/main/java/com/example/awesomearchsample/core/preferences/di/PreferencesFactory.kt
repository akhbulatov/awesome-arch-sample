package com.example.awesomearchsample.core.preferences.di

import android.content.Context
import com.example.awesomearchsample.core.preferences.AppPreferences

class PreferencesFactory(
    private val context: Context
) {

    val appPreferences: AppPreferences by lazy {
        AppPreferences(
            context = context
        )
    }
}