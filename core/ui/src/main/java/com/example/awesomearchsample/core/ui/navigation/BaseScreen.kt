package com.example.awesomearchsample.core.ui.navigation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey

abstract class BaseScreen : Screen {

    override val key: ScreenKey = uniqueScreenKey
}