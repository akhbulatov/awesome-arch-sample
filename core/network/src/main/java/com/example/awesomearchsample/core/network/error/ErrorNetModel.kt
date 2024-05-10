package com.example.awesomearchsample.core.network.error

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorNetModel(
    @SerialName("message") val message: String?
)
