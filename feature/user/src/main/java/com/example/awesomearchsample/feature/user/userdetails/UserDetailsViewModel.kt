package com.example.awesomearchsample.feature.user.userdetails

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.commonapi.util.launchCatching
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.user.usecase.GetUserDetailsUseCase
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsScreenDependencies

internal class UserDetailsViewModel(
    private val args: Args,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val errorHandler: UiErrorHandler
) : BaseViewModel<UserDetailsUiState, BaseUiEffect>(initialUiState = UserDetailsUiState()) {

    data class Args(
        val login: String
    )

    init {
        loadUserDetails()
    }

    private fun loadUserDetails() {
        viewModelScope.launchCatching(
            onFailure = { e ->
                errorHandler.proceed(e) { uiError ->
                    mutableUiState.value = UserDetailsUiState(initialError = uiError)
                }
            }
        ) {
            mutableUiState.value = UserDetailsUiState(isInitialLoading = true)

            val userDetails = getUserDetailsUseCase.invoke(login = args.login)
            mutableUiState.value = UserDetailsUiState(
                content = UserDetailsContent(userDetails = userDetails)
            )
        }
    }

    fun onErrorActionClick() {
        loadUserDetails()
    }

    companion object {
        fun viewModelFactory(
            args: Args,
            dependencies: UserDetailsScreenDependencies
        ) = viewModelFactory {
            initializer {
                UserDetailsViewModel(
                    args = args,
                    getUserDetailsUseCase = dependencies.getUserDetailsUseCase,
                    errorHandler = dependencies.uiErrorHandler
                )
            }
        }
    }
}
