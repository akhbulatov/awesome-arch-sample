package com.example.awesomearchsample.domain.repo.model

data class Repo(
    val id: Long,
    val name: String,
    val author: String,
    val description: String?,
    val inFavorites: Boolean = false
)
