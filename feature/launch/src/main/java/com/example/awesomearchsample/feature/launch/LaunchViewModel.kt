package com.example.awesomearchsample.feature.launch

import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.feature.launch.navigation.LaunchNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val launchNavigator: LaunchNavigator
) : BaseViewModel<Unit, LaunchUiEvent>(initialUiState = Unit) {

    init {
        viewModelScope.launch {
            mutableUiEvent.send(
                LaunchUiEvent.ResetAll(screen = launchNavigator.getMainHostScreen())
            )
        }
    }
}