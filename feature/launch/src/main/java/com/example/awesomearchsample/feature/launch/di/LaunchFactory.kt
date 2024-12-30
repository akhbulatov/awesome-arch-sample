package com.example.awesomearchsample.feature.launch.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.awesomearchsample.core.ui.util.getApplicationInstance
import com.example.awesomearchsample.domain.di.DomainFactory
import com.example.awesomearchsample.feature.launch.LaunchViewModel
import com.example.awesomearchsample.feature.launch.navigation.LaunchNavigator

class LaunchFactory(
    private val launchNavigator: LaunchNavigator,
    private val domainFactory: DomainFactory
) {

    fun createViewModelFactory() = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return LaunchViewModel(
                launchNavigator = launchNavigator,
                isFirstLaunchUseCase = domainFactory.appPreferencesDomainFactory.isFirstLaunchUseCase,
                setIsFirstLaunchUseCase = domainFactory.appPreferencesDomainFactory.setIsFirstLaunchUseCase
            ) as T
        }
    }
}

interface LaunchFactoryProvider {
    fun getLaunchFactory(): LaunchFactory
}

@Composable
fun rememberLaunchFactory(): LaunchFactory {
    val application = getApplicationInstance()
    return remember { (application as LaunchFactoryProvider).getLaunchFactory() }
}