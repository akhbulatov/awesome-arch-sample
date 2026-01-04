package com.example.awesomearchsample.feature.user.userdetails

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.user.usecase.GetUserDetailsUseCase
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsDependencies
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val login: String,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val errorHandler: UiErrorHandler
) : BaseViewModel<UserDetailsUiState, BaseUiEffect>(initialUiState = UserDetailsUiState.Loading) {

    init {
        loadUserDetails()
    }

    private fun loadUserDetails() {
        viewModelScope.launch {
            try {
                mutableUiState.value = UserDetailsUiState.Loading
                val userDetails = getUserDetailsUseCase.invoke(login = login)
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
        fun factory(
            login: String,
            dependencies: UserDetailsDependencies
        ) = viewModelFactory {
            initializer {
                UserDetailsViewModel(
                    login = login,
                    getUserDetailsUseCase = dependencies.getUserDetailsUseCase,
                    errorHandler = dependencies.uiErrorHandler
                )
            }
        }
    }
}
