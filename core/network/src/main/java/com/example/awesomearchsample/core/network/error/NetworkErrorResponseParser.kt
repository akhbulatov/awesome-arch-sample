package com.example.awesomearchsample.core.network.error

import kotlinx.serialization.json.Json

class NetworkErrorResponseParser(
    private val json: Json
) {

    fun parseError(response: String?): ErrorNetModel? {
        return if (!response.isNullOrBlank()) {
            try {
                json.decodeFromString<ErrorNetModel>(response)
            } catch (e: Exception) {
                ErrorNetModel(message = response)
            }
        } else {
            null
        }
    }
}