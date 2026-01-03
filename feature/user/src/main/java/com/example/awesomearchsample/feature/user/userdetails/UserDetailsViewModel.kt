package com.example.awesomearchsample.feature.user.userdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.user.usecase.GetUserDetailsUseCase
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsDependencies
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val login: String,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val errorHandler: UiErrorHandler
) : BaseViewModel<UserDetailsUiState, BaseUiEffect>(initialUiState = UserDetailsUiState()) {

    init {
        loadUserDetails()
    }

    private fun loadUserDetails() {
        viewModelScope.launch {
            try {
                mutableUiState.update { it.copy(emptyProgress = true) }
                val userDetails = getUserDetailsUseCase.invoke(login = login)
                mutableUiState.update { uiState.value.copy(userDetails = userDetails) }
            } catch (e: Exception) {
                errorHandler.proceed(
                    error = e,
                    errorListener = { uiError ->
                        mutableUiState.update { it.copy(emptyError = uiError) }
                    }
                )
            } finally {
                mutableUiState.update { it.copy(emptyProgress = false) }
            }
        }
    }

    fun onErrorActionClick() {
        mutableUiState.value = UserDetailsUiState()
        loadUserDetails()
    }

    companion object {
        fun factory(
            login: String,
            dependencies: UserDetailsDependencies
        ) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return UserDetailsViewModel(
                    login = login,
                    getUserDetailsUseCase = dependencies.getUserDetailsUseCase,
                    errorHandler = dependencies.uiErrorHandler
                ) as T
            }
        }
    }
}
