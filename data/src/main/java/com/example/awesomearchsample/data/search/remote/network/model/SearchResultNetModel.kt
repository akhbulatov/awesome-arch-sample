package com.example.awesomearchsample.data.search.remote.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchResultNetModel<T>(
    @SerialName("items") val items: T
)
