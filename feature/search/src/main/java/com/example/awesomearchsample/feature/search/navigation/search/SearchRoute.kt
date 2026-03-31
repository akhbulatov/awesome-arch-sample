package com.example.awesomearchsample.feature.search.navigation.search

import com.example.awesomearchsample.core.ui.navigation.AppNavigator
import com.example.awesomearchsample.core.ui.navigation.NavRoute
import kotlinx.serialization.Serializable

@Serializable
internal object SearchRoute : NavRoute

fun AppNavigator.navigateToSearch() {
    navigateTo(SearchRoute)
}
