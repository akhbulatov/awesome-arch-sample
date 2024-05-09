package com.example.awesomearchsample.feature.repo.internal.network

import com.google.gson.annotations.SerializedName

internal data class RepoDetailsNetModel(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: OwnerNetModel,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("forks_count") val forksCount: Int
)