package com.example.awesomearchsample.feature.search

import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect

internal sealed class SearchUiEffect : BaseUiEffect {
    data class NavigateToRepoDetails(val repoId: Long) : SearchUiEffect()
}
