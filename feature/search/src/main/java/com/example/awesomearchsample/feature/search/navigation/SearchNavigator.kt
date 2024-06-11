package com.example.awesomearchsample.feature.search.navigation

import cafe.adriel.voyager.core.screen.Screen

interface SearchNavigator {
    fun getRepoDetailsScreen(repoId: Long): Screen
}