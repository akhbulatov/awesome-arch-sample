package com.example.awesomearchsample.feature.repo.domain.repository

import com.example.awesomearchsample.domain.model.Repo

interface RepoRepository {
    suspend fun getRepos(): List<com.example.awesomearchsample.domain.model.Repo>
}