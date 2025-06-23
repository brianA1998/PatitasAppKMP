package com.patitasapp.kmp.authentication.domain.usecase

import com.patitasapp.kmp.authentication.domain.model.User
import com.patitasapp.kmp.authentication.domain.repository.AuthRepository

class SignInUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return authRepository.signIn(email, password)
    }
}