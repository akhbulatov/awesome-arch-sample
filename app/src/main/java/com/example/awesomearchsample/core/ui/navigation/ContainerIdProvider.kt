package com.example.awesomearchsample.core.ui.navigation

import androidx.annotation.IdRes

interface ContainerIdProvider {
    @get:IdRes val containerId: Int
}