package com.example.awesomearchsample.domain.user.model

data class UserDetails(
    val id: Long,
    val login: String,
    val name: String,
    val avatarUrl: String?,
    val location: String?,
    val bio: String?
)