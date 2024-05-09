package com.example.awesomearchsample.feature.repo.internal.network

import com.google.gson.annotations.SerializedName

data class OwnerNetModel(
    @SerializedName("login") val login: String
)