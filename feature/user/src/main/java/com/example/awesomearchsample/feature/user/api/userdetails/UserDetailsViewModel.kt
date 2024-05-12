package com.example.awesomearchsample.feature.user.api.userdetails

import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEvent
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.user.usecase.GetUserDetailsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = UserDetailsViewModel.Factory::class)
class UserDetailsViewModel @AssistedInject constructor(
    @Assisted private val login: String,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val errorHandler: UiErrorHandler
) : BaseViewModel<UserDetailsUiState, BaseUiEvent>(initialUiState = UserDetailsUiState()) {

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

    @AssistedFactory
    interface Factory {
        fun create(login: String): UserDetailsViewModel
    }
}