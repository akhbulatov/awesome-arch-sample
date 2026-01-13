package com.example.awesomearchsample.feature.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.core.ui.text.UiText
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult
import com.example.awesomearchsample.domain.search.usecase.GetSearchQueriesUseCase
import com.example.awesomearchsample.domain.search.usecase.GetSearchResultUseCase
import com.example.awesomearchsample.domain.search.usecase.SaveSearchQueryUseCase
import com.example.awesomearchsample.feature.search.di.SearchDependencies
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SearchViewModel(
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val getSearchQueriesUseCase: GetSearchQueriesUseCase,
    private val saveSearchQueryUseCase: SaveSearchQueryUseCase,
    private val errorHandler: UiErrorHandler
) : BaseViewModel<SearchUiState, SearchUiEffect>(initialUiState = SearchUiState.Idle()) {

    var queryInput by mutableStateOf(value = "")
        private set

    init {
        observeSearchQueries()
    }

    private fun observeSearchQueries() {
        getSearchQueriesUseCase.invoke()
            .onEach { searchQueries ->
                mutableUiState.update { state ->
                    when (state) {
                        is SearchUiState.Idle -> state.copy(recentQueries = searchQueries)
                        else -> state
                    }
                }
            }
            .catch { e ->
                errorHandler.proceed(error = e)
            }
            .launchIn(viewModelScope)
    }

    fun onSearchQueryInputChanged(query: String) {
        queryInput = query
    }

    fun onSearchActionClick() {
        saveSearchQuery(queryInput)
        loadSearchResult(queryInput)
    }

    private fun saveSearchQuery(query: String) {
        viewModelScope.launch {
            try {
                saveSearchQueryUseCase.invoke(query = SearchQuery(query))
            } catch (e: Exception) {
                errorHandler.proceed(e)
            }
        }
    }

    private fun loadSearchResult(query: String) {
        viewModelScope.launch {
            try {
                mutableUiState.value = SearchUiState.Loading
                val searchResult = getSearchResultUseCase.invoke(query)
                mutableUiState.value = if ((searchResult as SearchResult.Repos).data.isNotEmpty()) {
                    SearchUiState.Success(result = searchResult)
                } else {
                    SearchUiState.Error(
                        error = UiError(
                            title = UiText.Res(R.string.search_no_result)
                        )
                    )
                }
            } catch (e: Exception) {
                errorHandler.proceed(
                    error = e,
                    errorListener = { uiError ->
                        mutableUiState.value = SearchUiState.Error(error = uiError)
                    }
                )
            }
        }
    }

    fun onRepoResultItemClick(repo: Repo) {
        emitEffect(
            SearchUiEffect.NavigateToRepoDetails(repoId = repo.id)
        )
    }

    fun onErrorActionClick() {
        loadSearchResult(query = queryInput)
    }

    companion object {
        fun viewModelFactory(dependencies: SearchDependencies) = viewModelFactory {
            initializer {
                SearchViewModel(
                    getSearchResultUseCase = dependencies.getSearchResultUseCase,
                    getSearchQueriesUseCase = dependencies.getSearchQueriesUseCase,
                    saveSearchQueryUseCase = dependencies.saveSearchQueryUseCase,
                    errorHandler = dependencies.uiErrorHandler
                )
            }
        }
    }
}
