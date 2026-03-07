package com.example.awesomearchsample.feature.user.di

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.core.ui.util.getApplicationInstance
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsScreenDependencies

interface UserFeatureDependencies {
    val userDetailsScreenDependencies: UserDetailsScreenDependencies
}

interface UserFeatureDependenciesProvider {
    fun getUserFeatureDependencies(): UserFeatureDependencies
}

@Composable
internal fun rememberUserFeatureDependencies(): UserFeatureDependencies {
    val application = getApplicationInstance()
    return (application as UserFeatureDependenciesProvider).getUserFeatureDependencies()
}
