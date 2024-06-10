package com.example.awesomearchsample.data.user.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserDetailsNetModel(
    @SerialName("id") val id: Long,
    @SerialName("login") val login: String,
    @SerialName("name") val name: String,
    @SerialName("avatar_url") val avatarUrl: String?,
    @SerialName("location") val location: String?,
    @SerialName("bio") val bio: String?
)