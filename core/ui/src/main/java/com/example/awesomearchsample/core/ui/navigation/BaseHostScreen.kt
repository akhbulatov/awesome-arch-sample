package com.example.awesomearchsample.core.ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

abstract class BaseHostScreen(
    private val startScreen: Screen
) : BaseScreen() {

    @Composable
    override fun Content() {
        Navigator(
            screen = startScreen,
            key = "BaseHostScreenNavigator"
        )
    }
}