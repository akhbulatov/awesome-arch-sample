package com.example.awesomearchsample.feature.user.internal.network

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