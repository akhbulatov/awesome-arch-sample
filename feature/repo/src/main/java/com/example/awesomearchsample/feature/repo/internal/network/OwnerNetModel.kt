package com.example.awesomearchsample.feature.repo.internal.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OwnerNetModel(
    @SerialName("login") val login: String
)