package com.example.awesomearchsample.feature.launch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.awesomearchsample.core.ui.navigation.NavRoute
import com.example.awesomearchsample.feature.launch.di.LaunchScreenDependencies
import kotlinx.serialization.Serializable

@Serializable
object LaunchRoute : NavRoute

@Composable
internal fun LaunchScreen(
    dependencies: LaunchScreenDependencies,
    onNavigateToMainHost: () -> Unit
) {
    val viewModel = viewModel<LaunchViewModel>(
        factory = LaunchViewModel.viewModelFactory(dependencies = dependencies)
    )

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                LaunchUiEffect.NavigateToMainHost -> onNavigateToMainHost()
            }
        }
    }
}
