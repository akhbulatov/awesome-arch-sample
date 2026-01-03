package com.example.awesomearchsample.feature.repo.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ReposViewModel(
    private val repoNavigator: RepoNavigator,
    private val getReposUseCase: GetReposUseCase,
    private val errorHandler: UiErrorHandler,
    private val analyticsEventSender: AnalyticsEventSender
) : BaseViewModel<ReposUiState, ReposUiEffect>(initialUiState = ReposUiState()) {

    init {
        loadRepos()
    }

    private fun loadRepos() {
        viewModelScope.launch {
            try {
                mutableUiState.update { it.copy(emptyProgress = true) }
                val repos = getReposUseCase.invoke()
                mutableUiState.update { uiState.value.copy(repos = repos) }
            } catch (e: Exception) {
                errorHandler.proceed(
                    error = e,
                    errorListener = { uiError ->
                        mutableUiState.update { it.copy(emptyError = uiError) }
                    }
                )
            } finally {
                mutableUiState.update { it.copy(emptyProgress = false) }
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
        mutableUiState.value = ReposUiState()
        loadRepos()
    }

    fun onFavoritesClick(repo: Repo) {
        val updatedRepos = uiState.value.repos.updatedByToggleInFavorites(repoBy = repo)
        mutableUiState.update { it.copy(repos = updatedRepos) }
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
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return ReposViewModel(
                        repoNavigator = dependencies.repoNavigator,
                        getReposUseCase = dependencies.getReposUseCase,
                        errorHandler = dependencies.uiErrorHandler,
                        analyticsEventSender = dependencies.analyticsEventSender
                    ) as T
                }
            }
    }
}
