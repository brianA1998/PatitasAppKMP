package com.patitasapp.kmp.authentication.data.source

import com.patitasapp.kmp.authentication.domain.model.User
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

class FirebaseAuthDataSource : AuthDataSource {

    private val auth = Firebase.auth

    override suspend fun signIn(email: String, password: String): Result<User> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password)
            val user = User(
                uid = result.user?.uid ?: "",
                email = email,
                displayName = result.user?.displayName ?: ""
            )
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
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