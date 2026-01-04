package com.example.awesomearchsample.feature.repo.repos

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.analytics.api.AnalyticsEvents
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.updatedByToggleInFavorites
import com.example.awesomearchsample.domain.repo.usecase.GetReposUseCase
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender
import com.example.awesomearchsample.feature.repo.navigation.RepoNavigator
import com.example.awesomearchsample.feature.repo.repodetails.RepoDetailsScreen
import com.example.awesomearchsample.feature.repo.repos.di.ReposDependencies
import kotlinx.coroutines.launch

class ReposViewModel(
    private val repoNavigator: RepoNavigator,
    private val getReposUseCase: GetReposUseCase,
    private val errorHandler: UiErrorHandler,
    private val analyticsEventSender: AnalyticsEventSender
) : BaseViewModel<ReposUiState, ReposUiEffect>(initialUiState = ReposUiState.Loading) {

    init {
        loadRepos()
    }

    private fun loadRepos() {
        viewModelScope.launch {
            try {
                mutableUiState.value = ReposUiState.Loading
                val repos = getReposUseCase.invoke()
                mutableUiState.value = ReposUiState.Success(repos = repos)
            } catch (e: Exception) {
                errorHandler.proceed(
                    error = e,
                    errorListener = { uiError ->
                        mutableUiState.value = ReposUiState.Error(error = uiError)
                    }
                )
            }
        }
    }

    fun onSearchClick() {
        viewModelScope.launch {
            mutableUiEffect.send(
                ReposUiEffect.NavigateTo(repoNavigator.getSearchScreen())
            )
        }
    }

    fun onErrorActionClick() {
        loadRepos()
    }

    fun onFavoritesClick(repo: Repo) {
        val state = uiState.value
        if (state is ReposUiState.Success) {
            val updatedRepos = state.repos.updatedByToggleInFavorites(repoBy = repo)
            mutableUiState.value = state.copy(repos = updatedRepos)
        }
    }

    fun onRepoClick(repo: Repo) {
        viewModelScope.launch {
            mutableUiEffect.send(
                ReposUiEffect.NavigateTo(RepoDetailsScreen(repoId = repo.id))
            )
            analyticsEventSender.sendEvent(
                AnalyticsEvents.Repo.RepoClick(repoId = repo.id, repoName = repo.name)
            )
        }
    }

    companion object {
        fun factory(dependencies: ReposDependencies) =
            viewModelFactory {
                initializer {
                    ReposViewModel(
                        repoNavigator = dependencies.repoNavigator,
                        getReposUseCase = dependencies.getReposUseCase,
                        errorHandler = dependencies.uiErrorHandler,
                        analyticsEventSender = dependencies.analyticsEventSender
                    )
                }
            }
    }
}
