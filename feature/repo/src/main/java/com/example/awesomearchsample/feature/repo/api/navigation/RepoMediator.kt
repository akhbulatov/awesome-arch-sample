package com.example.awesomearchsample.feature.repo.api.navigation

import me.aartikov.alligator.Screen

interface RepoMediator {
    fun getUserDetailsScreen(login: String): Screen
}