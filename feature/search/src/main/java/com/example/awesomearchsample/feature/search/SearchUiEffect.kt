package com.example.awesomearchsample.feature.search

import cafe.adriel.voyager.core.screen.Screen
import com.example.awesomearchsample.core.ui.mvvm.BaseUiEffect

sealed class SearchUiEffect : BaseUiEffect {
    data class NavigateTo(val screen: Screen) : SearchUiEffect()
}