package com.example.awesomearchsample.data.user.remote.network

import com.example.awesomearchsample.data.user.remote.network.model.UserDetailsNetModel
import com.example.awesomearchsample.domain.user.model.UserDetails

internal fun UserDetailsNetModel.mapUserDetailsFromNet(): UserDetails {
    return UserDetails(
        id = id,
        login = login,
        name = name,
        avatarUrl = avatarUrl,
        location = location,
        bio = bio
    )
}
