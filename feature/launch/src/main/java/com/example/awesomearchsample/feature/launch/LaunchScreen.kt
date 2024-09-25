package com.example.awesomearchsample.feature.launch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.awesomearchsample.core.ui.navigation.BaseScreen

object LaunchScreen : BaseScreen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getViewModel<LaunchViewModel>()

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