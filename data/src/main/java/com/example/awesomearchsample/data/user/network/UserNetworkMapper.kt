package com.example.awesomearchsample.data.user.network

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