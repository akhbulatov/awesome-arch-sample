package com.example.awesomearchsample.feature.user.userdetails

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.user.usecase.GetUserDetailsUseCase
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsScreenDependencies
import kotlinx.coroutines.launch

internal class UserDetailsViewModel(
    private val args: Args,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val errorHandler: UiErrorHandler
) : BaseViewModel<UserDetailsUiState, BaseUiEffect>(initialUiState = UserDetailsUiState.Initial) {

    data class Args(
        val login: String
    )

    init {
        loadUserDetails()
    }

    private fun loadUserDetails() {
        viewModelScope.launch {
            try {
                mutableUiState.value = UserDetailsUiState.Loading
                val userDetails = getUserDetailsUseCase.invoke(login = args.login)
                mutableUiState.value = UserDetailsUiState.Success(userDetails = userDetails)
            } catch (e: Exception) {
                errorHandler.proceed(
                    error = e,
                    errorListener = { uiError ->
                        mutableUiState.value = UserDetailsUiState.Error(error = uiError)
                    }
                )
            }
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
