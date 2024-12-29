package com.example.awesomearchsample.feature.user.userdetails.di

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.awesomearchsample.core.commonfactory.di.CoreFactory
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
    val userDetailsFactory: UserDetailsFactory
}

@Composable
fun getUserDetailsFactory(): UserDetailsFactory {
    val application = (LocalContext.current.applicationContext as Application)
    return (application as UserDetailsFactoryProvider).userDetailsFactory
}