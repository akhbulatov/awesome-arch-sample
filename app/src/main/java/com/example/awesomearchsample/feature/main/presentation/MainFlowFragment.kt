package com.example.awesomearchsample.feature.main.presentation

import com.example.awesomearchsample.Screens
import com.example.awesomearchsample.core.ui.base.BaseFlowFragment
import dagger.hilt.android.AndroidEntryPoint
import me.aartikov.alligator.Screen

@AndroidEntryPoint
class MainFlowFragment : BaseFlowFragment() {

    override val startScreen: Screen
        get() = Screens.Repos
}