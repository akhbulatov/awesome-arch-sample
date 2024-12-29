package com.example.awesomearchsample.feature.launch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.awesomearchsample.core.ui.navigation.BaseScreen
import com.example.awesomearchsample.feature.launch.di.getLaunchFactory

object LaunchScreen : BaseScreen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val factory = getLaunchFactory()
        val viewModel = viewModel<LaunchViewModel>(factory = factory.createViewModelFactory())

        LaunchedEffect(Unit) {
            viewModel.uiEffect.collect { effect ->
                when (effect) {
                    is LaunchUiEffect.ResetAll -> {
                        navigator.replaceAll(effect.screen)
                    }
                }
            }
        }
    }
}