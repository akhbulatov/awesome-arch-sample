package com.example.awesomearchsample.domain.repo.usecase

import com.example.awesomearchsample.domain.repo.model.RepoDetails
import com.example.awesomearchsample.domain.repo.repository.RepoRepository

class GetRepoDetailsUseCase(
    private val repoRepository: RepoRepository
) {

    suspend operator fun invoke(repoId: Long): RepoDetails {
        return repoRepository.getRepoDetails(repoId)
    }
}