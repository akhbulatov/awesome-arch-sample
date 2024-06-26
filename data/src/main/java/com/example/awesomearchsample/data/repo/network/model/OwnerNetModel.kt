package com.example.awesomearchsample.data.repo.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OwnerNetModel(
    @SerialName("login") val login: String
)