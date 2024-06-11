package com.example.awesomearchsample.feature.search

import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.core.ui.error.UiError
import com.example.awesomearchsample.core.ui.error.UiErrorHandler
import com.example.awesomearchsample.core.ui.mvvm.BaseViewModel
import com.example.awesomearchsample.core.ui.util.ResourceManager
import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.search.model.SearchResult
import com.example.awesomearchsample.domain.search.usecase.GetSearchResultUseCase
import com.example.awesomearchsample.feature.search.navigation.SearchNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchNavigator: SearchNavigator,
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val errorHandler: UiErrorHandler,
    private val resourceManager: ResourceManager
) : BaseViewModel<SearchUiState, SearchUiEvent>(initialUiState = SearchUiState()) {

    fun onSearchActionClick(query: String) {
        loadSearchResult(query)
    }

    private fun loadSearchResult(query: String) {
        viewModelScope.launch {
            try {
                mutableUiState.value = SearchUiState(emptyProgress = true)
                val searchResult = getSearchResultUseCase.invoke(query)
                mutableUiState.update { state ->
                    if ((searchResult as SearchResult.Repos).data.isNotEmpty()) {
                        state.copy(result = searchResult)
                    } else {
                        state.copy(
                            emptyError = UiError(title = resourceManager.getString(R.string.search_no_result))
                        )
                    }
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