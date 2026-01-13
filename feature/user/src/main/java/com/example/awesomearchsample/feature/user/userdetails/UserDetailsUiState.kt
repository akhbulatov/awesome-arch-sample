package com.example.awesomearchsample.feature.user.userdetails

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.mvvm.SuccessState
import com.example.awesomearchsample.domain.user.model.UserDetails

internal sealed class UserDetailsUiState {
    data object Loading : UserDetailsUiState()
    data class Error(val error: UiError) : UserDetailsUiState()
    data class Success(val userDetails: UserDetails) : UserDetailsUiState(), SuccessState<UserDetails> {
        override val data: UserDetails
            get() = userDetails
    }
}
