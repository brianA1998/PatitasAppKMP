package com.patitasapp.kmp.authentication.data.source

import com.patitasapp.kmp.authentication.domain.model.User

class FirebaseAuthDataSource : AuthDataSource {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun signIn(email: String, password: String): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(email: String, password: String, name: String): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun signOut(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): Result<User?> {
        TODO("Not yet implemented")
    }
}