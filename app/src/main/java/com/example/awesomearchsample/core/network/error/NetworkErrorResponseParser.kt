package com.example.awesomearchsample.core.network.error

import com.google.gson.Gson
import javax.inject.Inject

class NetworkErrorResponseParser @Inject constructor(
    private val gson: Gson
) {

    fun parseError(response: String?): ErrorNetModel? {
        return try {
            gson.fromJson(response, ErrorNetModel::class.java)
        } catch (e: Exception) {
            if (!response.isNullOrBlank()) {
                ErrorNetModel(message = response)
            } else {
                null
            }
        }
    }
}