package com.example.awesomearchsample.feature.repo.navigation

import cafe.adriel.voyager.core.screen.Screen

interface RepoNavigator {
    fun getUserDetailsScreen(login: String): Screen
    fun getSearchScreen(): Screen
}