package com.example.awesomearchsample.feature.repo.repos

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.commonapi.util.launchCatching
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.usecase.GetReposUseCase
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEventSender
import com.example.awesomearchsample.feature.common.analytics.AnalyticsEvents
import com.example.awesomearchsample.feature.repo.repos.di.ReposScreenDependencies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

internal class ReposViewModel(
    private val getReposUseCase: GetReposUseCase,
    private val errorHandler: UiErrorHandler,
    private val analyticsEventSender: AnalyticsEventSender
) : BaseViewModel<ReposUiEffect>() {

    val uiState: StateFlow<ReposUiState>
        field = MutableStateFlow<ReposUiState>(ReposUiState.Loading)

    init {
        loadRepos()
    }

    private fun loadRepos() {
        viewModelScope.launchCatching(
            onFailure = { e ->
                errorHandler.proceed(e) { uiError ->
                    uiState.value = ReposUiState.Error(uiError)
                }
            }
        ) {
            uiState.value = ReposUiState.Loading

            val repos = getReposUseCase.invoke()
            uiState.value = ReposUiState.Success(repos = repos)
        }
    }

    private fun refreshRepos() {
        if (uiState.value !is ReposUiState.Success) return

        viewModelScope.launchCatching(
            onFailure = { e ->
                errorHandler.proceed(e) { uiError ->
                    emitEffect(
                        ReposUiEffect.ShowErrorMessage(message = uiError.title)
                    )
                }
            },
            onFinally = {
                updateSuccess { it.copy(isRefreshing = false) }
            }
        ) {
            updateSuccess { it.copy(isRefreshing = true) }

            val repos = getReposUseCase.invoke()
            updateSuccess { it.copy(repos = repos) }
        }
    }

    fun onSearchClick() {
        emitEffect(ReposUiEffect.NavigateToSearch)
    }

    fun onSettingsClick() {
        emitEffect(ReposUiEffect.NavigateToSettings)
    }

    fun onErrorActionClick() {
        loadRepos()
    }

    fun onRefresh() {
        refreshRepos()
    }

    fun onFavoritesClick(repo: Repo) {
        updateSuccess { state ->
            state.copy(
                repos = state.repos.withFavoriteToggled(repoId = repo.id)
            )
        }
    }

    private fun updateSuccess(
        transform: (ReposUiState.Success) -> ReposUiState.Success
    ) {
        uiState.update { state ->
            if (state is ReposUiState.Success) transform(state) else state
        }
    }

    fun onRepoClick(repo: Repo) {
        emitEffect(
            ReposUiEffect.NavigateToRepoDetails(repoId = repo.id)
        )
        analyticsEventSender.sendEvent(
            AnalyticsEvents.Repo.RepoClick(
                repoId = repo.id,
                repoName = repo.name
            )
        )
    }

    fun onEmptyDataActionClick() {
        refreshRepos()
    }

    companion object {
        fun viewModelFactory(dependencies: ReposScreenDependencies) = viewModelFactory {
            initializer {
                ReposViewModel(
                    getReposUseCase = dependencies.getReposUseCase,
                    errorHandler = dependencies.uiErrorHandler,
                    analyticsEventSender = dependencies.analyticsEventSender
                )
            }
        }
    }
}
