package com.example.awesomearchsample.feature.user.userdetails.di

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.domain.user.usecase.GetUserDetailsUseCase
import com.example.awesomearchsample.feature.user.di.rememberUserFeatureDependencies

interface UserDetailsDependencies {
    val getUserDetailsUseCase: GetUserDetailsUseCase
    val uiErrorHandler: UiErrorHandler
}

@Composable
internal fun rememberUserDetailsDependencies(): UserDetailsDependencies {
    return rememberUserFeatureDependencies().userDetailsDependencies
}
