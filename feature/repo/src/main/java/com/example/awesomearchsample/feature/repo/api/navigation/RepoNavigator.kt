package com.example.awesomearchsample.feature.repo.api.navigation

import cafe.adriel.voyager.core.screen.Screen

interface RepoNavigator {
    fun getUserDetailsScreen(login: String): Screen
}