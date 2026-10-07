package com.example.awesomearchsample.feature.user.userdetails

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.user.model.UserDetails

internal sealed interface UserDetailsUiState {
    data object Loading: UserDetailsUiState
    data class Error(val error: UiError): UserDetailsUiState
    data class Success(val userDetails: UserDetails): UserDetailsUiState
}
