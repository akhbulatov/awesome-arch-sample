package com.example.awesomearchsample.domain.repo.repository

import com.example.awesomearchsample.model.Repo

interface RepoRepository {
    suspend fun getRepos(): List<Repo>
}