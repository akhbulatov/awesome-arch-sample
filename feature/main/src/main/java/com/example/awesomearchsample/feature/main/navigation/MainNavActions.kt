package com.example.awesomearchsample.feature.main.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.feature.main.host.MainHostRoute

fun NavBackStack<NavKey>.navigateToMainHost() {
    clear()
    add(MainHostRoute)
}
