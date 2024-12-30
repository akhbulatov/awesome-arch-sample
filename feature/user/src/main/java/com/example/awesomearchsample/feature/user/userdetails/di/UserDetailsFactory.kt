package com.example.awesomearchsample.feature.user.userdetails.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.awesomearchsample.core.corefactory.di.CoreFactory
import com.example.awesomearchsample.core.ui.util.getApplicationInstance
import com.example.awesomearchsample.domain.di.DomainFactory
import com.example.awesomearchsample.feature.user.userdetails.UserDetailsViewModel

class UserDetailsFactory(
    private val domainFactory: DomainFactory,
    private val coreFactory: CoreFactory,
) {

    fun createViewModelFactory(
        login: String
    ) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return UserDetailsViewModel(
                login = login,
                getUserDetailsUseCase = domainFactory.userDomainFactory.getUserDetailsUseCase,
                errorHandler = coreFactory.uiFactory.uiErrorHandler,
            ) as T
        }
    }
}

interface UserDetailsFactoryProvider {
    fun getUserDetailsFactory(): UserDetailsFactory
}

@Composable
fun rememberUserDetailsFactory(): UserDetailsFactory {
    val application = getApplicationInstance()
    return (application as UserDetailsFactoryProvider).getUserDetailsFactory()
}