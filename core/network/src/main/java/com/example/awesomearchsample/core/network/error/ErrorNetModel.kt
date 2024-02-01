package com.example.awesomearchsample.core.network.error

import com.google.gson.annotations.SerializedName

data class ErrorNetModel(
    @SerializedName("message") val message: String?
)
