package com.example.awesomearchsample.data.repo.network

import com.google.gson.annotations.SerializedName

data class RepoNetModel(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: OwnerNetModel,
    @SerializedName("description") val description: String?,
) {

    data class OwnerNetModel(
        @SerializedName("login") val login: String
    )
}