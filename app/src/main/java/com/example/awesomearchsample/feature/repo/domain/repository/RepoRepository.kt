package com.example.awesomearchsample.feature.repo.domain.repository

import com.example.awesomearchsample.feature.repo.domain.model.Repo

interface RepoRepository {
    suspend fun getRepos(): List<Repo>
}