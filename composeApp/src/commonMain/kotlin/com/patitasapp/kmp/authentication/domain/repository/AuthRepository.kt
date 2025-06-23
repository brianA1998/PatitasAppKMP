package com.patitasapp.kmp.authentication.domain.repository

import com.patitasapp.kmp.authentication.domain.model.User

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Result<User>
    suspend fun signUp(email: String, password: String): Result<User>
    suspend fun signOut(): Result<Unit>
    suspend fun getCurrentUser(): Result<User?>
}