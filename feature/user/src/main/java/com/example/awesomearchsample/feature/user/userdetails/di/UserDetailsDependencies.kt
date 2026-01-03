package com.example.awesomearchsample.feature.user.userdetails.di

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.util.getApplicationInstance
import com.example.awesomearchsample.domain.user.usecase.GetUserDetailsUseCase

interface UserDetailsDependencies {
    val getUserDetailsUseCase: GetUserDetailsUseCase
    val uiErrorHandler: UiErrorHandler
}

interface UserDetailsDependenciesProvider {
    fun getUserDetailsDependencies(): UserDetailsDependencies
}

@Composable
fun rememberUserDetailsDependencies(): UserDetailsDependencies {
    val application = getApplicationInstance()
    return (application as UserDetailsDependenciesProvider).getUserDetailsDependencies()
}
