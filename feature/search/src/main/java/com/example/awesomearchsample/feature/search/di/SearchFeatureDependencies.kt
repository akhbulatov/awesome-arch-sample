package com.example.awesomearchsample.feature.search.di

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.core.ui.util.getApplicationInstance

interface SearchFeatureDependencies {
    val searchScreenDependencies: SearchScreenDependencies
}

interface SearchFeatureDependenciesProvider {
    fun getSearchFeatureDependencies(): SearchFeatureDependencies
}

@Composable
internal fun rememberSearchFeatureDependencies(): SearchFeatureDependencies {
    val application = getApplicationInstance()
    return (application as SearchFeatureDependenciesProvider).getSearchFeatureDependencies()
}
