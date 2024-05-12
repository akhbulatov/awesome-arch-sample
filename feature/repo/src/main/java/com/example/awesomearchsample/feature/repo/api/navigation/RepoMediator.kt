package com.example.awesomearchsample.feature.repo.api.navigation

import cafe.adriel.voyager.core.screen.Screen

interface RepoMediator {
    fun getUserDetailsScreen(login: String): Screen
}