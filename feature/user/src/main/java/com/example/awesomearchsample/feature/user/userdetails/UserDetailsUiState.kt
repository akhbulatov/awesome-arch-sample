package com.example.awesomearchsample.feature.user.userdetails

import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.domain.user.model.UserDetails

internal data class UserDetailsUiState(
    val isInitialLoading: Boolean = false,
    val initialError: UiError? = null,
    val content: UserDetailsContent? = null
)

internal data class UserDetailsContent(
    val userDetails: UserDetails
)
