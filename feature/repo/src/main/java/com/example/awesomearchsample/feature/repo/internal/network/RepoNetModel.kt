package com.example.awesomearchsample.feature.repo.internal.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RepoNetModel(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("owner") val owner: OwnerNetModel,
    @SerialName("description") val description: String?,
)