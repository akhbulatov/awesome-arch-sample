package com.example.awesomearchsample.domain.user.usecase

import com.example.awesomearchsample.domain.user.model.UserDetails
import com.example.awesomearchsample.domain.user.repository.UserRepository
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(login: String): UserDetails {
        return userRepository.getUserDetails(login)
    }
}