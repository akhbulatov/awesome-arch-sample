package com.example.awesomearchsample.feature.repo.domain.usecase

import com.example.awesomearchsample.domain.model.Repo
import com.example.awesomearchsample.feature.repo.domain.repository.RepoRepository
import javax.inject.Inject

class GetReposUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {

    suspend operator fun invoke(): List<com.example.awesomearchsample.domain.model.Repo> {
        return repoRepository.getRepos()
    }
}