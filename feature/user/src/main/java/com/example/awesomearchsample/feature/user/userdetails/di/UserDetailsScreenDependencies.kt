package com.example.awesomearchsample.feature.user.userdetails.di

import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.domain.user.usecase.GetUserDetailsUseCase

interface UserDetailsScreenDependencies {
    val getUserDetailsUseCase: GetUserDetailsUseCase
    val uiErrorHandler: UiErrorHandler
}
