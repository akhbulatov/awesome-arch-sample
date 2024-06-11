package com.example.awesomearchsample.feature.search

import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.core.ui.util.ResourceManager
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.search.model.SearchQuery
import com.example.awesomearchsample.domain.search.model.SearchResult
import com.example.awesomearchsample.domain.search.usecase.GetSearchQueriesUseCase
import com.example.awesomearchsample.domain.search.usecase.GetSearchResultUseCase
import com.example.awesomearchsample.domain.search.usecase.SaveSearchQueryUseCase
import com.example.awesomearchsample.feature.search.navigation.SearchNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchNavigator: SearchNavigator,
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val getSearchQueriesUseCase: GetSearchQueriesUseCase,
    private val saveSearchQueryUseCase: SaveSearchQueryUseCase,
    private val errorHandler: UiErrorHandler,
    private val resourceManager: ResourceManager
) : BaseViewModel<SearchUiState, SearchUiEvent>(initialUiState = SearchUiState()) {

    init {
        observeSearchQueries()
    }

    private fun observeSearchQueries() {
        getSearchQueriesUseCase.invoke()
            .onEach { searchQueries ->
                mutableUiState.update { state ->
                    state.copy(recentQueries = if (state.result == null) searchQueries else emptyList())
                }
            }
            .catch { e ->
                errorHandler.proceed(error = e)
            }
            .launchIn(viewModelScope)
    }

    fun onSearchActionClick(query: String) {
        saveSearchQuery(query)
        loadSearchResult(query)
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
                mutableUiState.value = SearchUiState(emptyProgress = true)
                val searchResult = getSearchResultUseCase.invoke(query)
                mutableUiState.value = if ((searchResult as SearchResult.Repos).data.isNotEmpty()) {
                    SearchUiState(result = searchResult)
                } else {
                    SearchUiState(emptyError = UiError(title = resourceManager.getString(R.string.search_no_result)))
                }
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

    fun onRepoResultItemClick(repo: Repo) {
        viewModelScope.launch {
            mutableUiEvent.send(
                SearchUiEvent.NavigateTo(searchNavigator.getRepoDetailsScreen(repoId = repo.id))
            )
        }
    }

    fun onErrorActionClick() {

    }
}