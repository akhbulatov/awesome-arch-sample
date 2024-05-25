package com.example.awesomearchsample.feature.user.userdetails

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.user.model.UserDetails

data class UserDetailsUiState(
    val emptyProgress: Boolean = false,
    val emptyError: UiError? = null,
    val userDetails: UserDetails? = null
)