package com.example.awesomearchsample.feature.launch.navigation

import cafe.adriel.voyager.core.screen.Screen

interface LaunchNavigator {
    fun getMainHostScreen(): Screen
}