package com.patitasapp.kmp.authentication.domain.usecase

import com.patitasapp.kmp.authentication.domain.model.User
import com.patitasapp.kmp.authentication.domain.repository.AuthRepository
import com.patitasapp.kmp.core.utils.Logger

class SignInUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        Logger.authDomain("Iniciando caso de uso SignInUseCase con email: $email")

        return authRepository.signIn(email, password).also { result ->
            if (result.isSuccess) {
                val user = result.getOrNull()
                Logger.authDomain("SignInUseCase completado exitosamente. Usuario: ${user?.email}, UID: ${user?.uid}")
            } else {
                val error = result.exceptionOrNull()
                Logger.authDomain("SignInUseCase fallido. Error: ${error?.message}")
            }
        }
    }
}