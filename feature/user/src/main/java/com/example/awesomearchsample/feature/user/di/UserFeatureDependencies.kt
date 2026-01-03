package com.example.awesomearchsample.feature.user.di

import androidx.compose.runtime.Composable
import com.example.awesomearchsample.core.ui.util.getApplicationInstance
import com.example.awesomearchsample.feature.user.userdetails.di.UserDetailsDependencies

interface UserFeatureDependencies {
    val userDetailsDependencies: UserDetailsDependencies
}

interface UserFeatureDependenciesProvider {
    fun getUserFeatureDependencies(): UserFeatureDependencies
}

@Composable
fun rememberUserFeatureDependencies(): UserFeatureDependencies {
    val application = getApplicationInstance()
    return (application as UserFeatureDependenciesProvider).getUserFeatureDependencies()
}
