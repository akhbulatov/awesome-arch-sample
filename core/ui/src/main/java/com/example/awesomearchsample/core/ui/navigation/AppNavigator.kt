package com.example.awesomearchsample.core.ui.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

interface AppNavigator {
    fun navigateTo(destination: NavKey)
    fun back()
    fun replace(destination: NavKey)
    fun resetTo(destination: NavKey)
}

fun backStackAppNavigator(
    backStack: NavBackStack<NavKey>
): AppNavigator = BackStackAppNavigator(backStack)

internal class BackStackAppNavigator(
    private val backStack: NavBackStack<NavKey>
) : AppNavigator {
    override fun navigateTo(destination: NavKey) {
        backStack.add(destination)
    }

    override fun back() {
        backStack.removeLastOrNull()
    }

    override fun replace(destination: NavKey) {
        backStack.removeLastOrNull()
        backStack.add(destination)
    }

    override fun resetTo(destination: NavKey) {
        backStack.clear()
        backStack.add(destination)
    }
}
