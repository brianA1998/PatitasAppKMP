package com.patitasapp.kmp.authentication.data.repository

import com.patitasapp.kmp.authentication.data.source.AuthDataSource
import com.patitasapp.kmp.authentication.domain.model.User
import com.patitasapp.kmp.authentication.domain.repository.AuthRepository
import com.patitasapp.kmp.core.utils.Logger

class AuthRepositoryImpl(private val dataSource: AuthDataSource) : AuthRepository {

    override suspend fun signIn(email: String, password: String): Result<User> {
        Logger.authData("Repositorio: iniciando signIn con email: $email")

        return dataSource.signIn(email, password).also { result ->
            if (result.isSuccess) {
                val user = result.getOrNull()
                Logger.authData("Repositorio: signIn exitoso. Usuario: ${user?.email}, UID: ${user?.uid}")
            } else {
                val error = result.exceptionOrNull()
                Logger.authData("Repositorio: signIn fallido. Error: ${error?.message}")
            }
        }
    }

    override suspend fun signUp(email: String, password: String): Result<User> {
        Logger.authData("Repositorio: iniciando signUp con email: $email")
        return dataSource.signUp(email, password, "Default Name")
    }

    override suspend fun signOut(): Result<Unit> {
        Logger.authData("Repositorio: iniciando signOut")
        return dataSource.signOut()
    }

    override suspend fun getCurrentUser(): Result<User?> {
        Logger.authData("Repositorio: obteniendo usuario actual")
        return dataSource.getCurrentUser()
    }
}