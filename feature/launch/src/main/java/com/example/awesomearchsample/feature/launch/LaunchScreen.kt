package com.example.awesomearchsample.feature.launch

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.awesomearchsample.core.ui.mvvm.CollectUiEffects
import com.example.awesomearchsample.feature.launch.di.LaunchScreenDependencies

@Composable
internal fun LaunchScreen(
    dependencies: LaunchScreenDependencies,
    onNavigateToMainHost: () -> Unit
) {
    val viewModel = viewModel<LaunchViewModel>(
        factory = LaunchViewModel.viewModelFactory(dependencies = dependencies)
    )

    CollectUiEffects(viewModel.uiEffects) { effect ->
        when (effect) {
            LaunchUiEffect.NavigateToMainHost -> onNavigateToMainHost()
        }
    }
}
