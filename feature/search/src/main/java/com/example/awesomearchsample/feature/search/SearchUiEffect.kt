package com.example.awesomearchsample.feature.search

import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect

sealed class SearchUiEffect : BaseUiEffect {
    data class NavigateToRepoDetails(val repoId: Long) : SearchUiEffect()
}
