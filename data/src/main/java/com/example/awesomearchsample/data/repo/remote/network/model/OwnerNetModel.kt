package com.example.awesomearchsample.data.repo.remote.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class OwnerNetModel(
    @SerialName("login") val login: String
)
