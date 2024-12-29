package com.example.awesomearchsample.feature.launch.di

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
    val launchFactory: LaunchFactory
}

@Composable
fun getLaunchFactory(): LaunchFactory {
    val application = (LocalContext.current.applicationContext as Application)
    return (application as LaunchFactoryProvider).launchFactory
}