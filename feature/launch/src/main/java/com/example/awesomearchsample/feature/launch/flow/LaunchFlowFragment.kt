package com.example.awesomearchsample.feature.launch.flow

import com.example.awesomearchsample.core.ui.base.BaseFlowFragment
import com.example.awesomearchsample.feature.launch.navigation.LaunchScreens
import dagger.hilt.android.AndroidEntryPoint
import me.aartikov.alligator.Screen

@AndroidEntryPoint
class LaunchFlowFragment : BaseFlowFragment() {

    override val startScreen: Screen
        get() = LaunchScreens.Launch
}