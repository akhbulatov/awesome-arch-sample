package com.example.awesomearchsample.feature.search.di

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.core.ui.util.getApplicationInstance

interface SearchFeatureDependencies {
    val searchDependencies: SearchDependencies
}

interface SearchFeatureDependenciesProvider {
    fun getSearchFeatureDependencies(): SearchFeatureDependencies
}

@Composable
fun rememberSearchFeatureDependencies(): SearchFeatureDependencies {
    val application = getApplicationInstance()
    return (application as SearchFeatureDependenciesProvider).getSearchFeatureDependencies()
}
