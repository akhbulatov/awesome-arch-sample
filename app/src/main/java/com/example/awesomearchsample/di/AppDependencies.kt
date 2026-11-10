package com.example.awesomearchsample.di

import com.example.awesomearchsample.feature.launch.di.LaunchFeatureDependencies
import com.example.awesomearchsample.feature.repo.di.RepoFeatureDependencies
import com.example.awesomearchsample.feature.search.di.SearchFeatureDependencies
import com.example.awesomearchsample.feature.settings.di.SettingsFeatureDependencies
import com.example.awesomearchsample.feature.user.di.UserFeatureDependencies

interface AppDependencies {
    val launchFeatureDependencies: LaunchFeatureDependencies
    val repoFeatureDependencies: RepoFeatureDependencies
    val searchFeatureDependencies: SearchFeatureDependencies
    val settingsFeatureDependencies: SettingsFeatureDependencies
    val userFeatureDependencies: UserFeatureDependencies
}
