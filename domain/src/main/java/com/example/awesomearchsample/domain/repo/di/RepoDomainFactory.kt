package com.example.awesomearchsample.domain.repo.di

import com.example.awesomearchsample.domain.repo.repository.RepoRepository
import com.example.awesomearchsample.domain.repo.usecase.GetRepoDetailsUseCase
import com.example.awesomearchsample.domain.repo.usecase.GetReposUseCase

class RepoDomainFactory(
    repoRepository: RepoRepository
) {

    val getReposUseCase: GetReposUseCase = GetReposUseCase(
        repoRepository = repoRepository
    )
    val getRepoDetailsUseCase: GetRepoDetailsUseCase = GetRepoDetailsUseCase(
        repoRepository = repoRepository
    )
}