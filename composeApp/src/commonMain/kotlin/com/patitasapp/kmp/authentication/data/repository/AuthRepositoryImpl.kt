package com.patitasapp.kmp.authentication.data.repository

import com.patitasapp.kmp.authentication.data.source.AuthDataSource
import com.patitasapp.kmp.authentication.domain.model.User
import com.patitasapp.kmp.authentication.domain.repository.AuthRepository

class AuthRepositoryImpl(private val dataSource: AuthDataSource) : AuthRepository {

    override suspend fun signIn(email: String, password: String): Result<User> {
        return dataSource.signIn(email, password)
    }

    override suspend fun signUp(email: String, password: String): Result<User> {
        return dataSource.signUp(email, password, "Default Name")
    }

    override suspend fun signOut(): Result<Unit> {
        return dataSource.signOut()
    }

    override suspend fun getCurrentUser(): Result<User?> {
        return dataSource.getCurrentUser()
    }
}