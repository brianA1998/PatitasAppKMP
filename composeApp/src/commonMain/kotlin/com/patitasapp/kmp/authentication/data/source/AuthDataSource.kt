package com.patitasapp.kmp.authentication.data.source

import com.patitasapp.kmp.authentication.domain.model.User

interface AuthDataSource {
    suspend fun signIn(email: String, password: String): Result<User>
    suspend fun signUp(email: String, password: String, name: String): Result<User>
    suspend fun signOut(): Result<Unit>
    suspend fun getCurrentUser(): Result<User?>
}