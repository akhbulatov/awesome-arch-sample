package com.example.awesomearchsample.domain.repo.model

data class RepoDetails(
    val id: Long,
    val name: String,
    val author: String,
    val description: String?,
    val starsCount: Int,
    val forksCount: Int
)