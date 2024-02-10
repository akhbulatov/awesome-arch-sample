package com.example.awesomearchsample.feature.main.flow

import com.example.awesomearchsample.core.ui.base.BaseFlowFragment
import com.example.awesomearchsample.feature.main.navigation.MainMediator
import dagger.hilt.android.AndroidEntryPoint
import me.aartikov.alligator.Screen
import javax.inject.Inject

@AndroidEntryPoint
class MainFlowFragment : BaseFlowFragment() {

    @Inject lateinit var mainMediator: MainMediator

    override val startScreen: Screen
        get() = mainMediator.getReposScreen()
}