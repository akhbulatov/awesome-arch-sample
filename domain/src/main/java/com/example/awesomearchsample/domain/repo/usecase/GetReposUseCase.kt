package com.example.awesomearchsample.domain.repo.usecase

import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import javax.inject.Inject

class GetReposUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) {

    suspend operator fun invoke(): List<Repo> {
        return repoRepository.getRepos()
    }
}