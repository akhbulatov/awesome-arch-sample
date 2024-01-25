package com.example.awesomearchsample.feature.repo.presentation.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awesomearchsample.feature.repo.domain.usecase.GetReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val getReposUseCase: GetReposUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData(ReposUiState())
    val uiState: LiveData<ReposUiState> = _uiState

    init {
        loadRepos()
    }

    private fun loadRepos() {
        viewModelScope.launch {
            try {
                val repos = getReposUseCase.invoke()
                _uiState.value = uiState.value?.copy(repos = repos)
            } catch (e: Exception) {
                e.printStackTrace() // TODO
            }
        }
    }
}