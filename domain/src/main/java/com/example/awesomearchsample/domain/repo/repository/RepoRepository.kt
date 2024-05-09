package com.example.awesomearchsample.domain.repo.repository

import com.example.awesomearchsample.domain.repo.model.Repo
import com.example.awesomearchsample.domain.repo.model.RepoDetails

interface RepoRepository {
    suspend fun getRepos(): List<Repo>

    suspend fun getRepoDetails(repoId: Long): RepoDetails
}