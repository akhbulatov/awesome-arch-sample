package com.example.awesomearchsample.domain.user.di

import com.example.awesomearchsample.domain.user.repository.UserRepository
import com.example.awesomearchsample.domain.user.usecase.GetUserDetailsUseCase

class UserDomainFactory(
    userRepository: UserRepository
) {

    val getUserDetailsUseCase: GetUserDetailsUseCase = GetUserDetailsUseCase(
        userRepository = userRepository
    )
}