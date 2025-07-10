package com.patitasapp.kmp.authentication.data.source

import com.patitasapp.kmp.authentication.domain.model.User
import com.patitasapp.kmp.core.utils.Logger
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

class FirebaseAuthDataSource : AuthDataSource {

    private val auth = Firebase.auth

    override suspend fun signIn(email: String, password: String): Result<User> {
        Logger.authFirebase("Firebase: iniciando signInWithEmailAndPassword con email: $email")

        return try {
            Logger.authFirebase("Firebase: intentando autenticación...")
            val result = auth.signInWithEmailAndPassword(email, password)

            Logger.authFirebase("Firebase: autenticación exitosa, uid=${result.user?.uid}")
            val user = User(
                uid = result.user?.uid ?: "",
                email = email,
                displayName = result.user?.displayName ?: ""
            )
            Logger.authFirebase("Firebase: objeto User creado con uid=${user.uid}, email=${user.email}")

            Result.success(user)
        } catch (e: Exception) {
            Logger.authFirebase("Firebase: error en autenticación - ${e}: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun signUp(email: String, password: String, name: String): Result<User> {
        Logger.authFirebase("Firebase: signUp aún no implementado")
        TODO("Not yet implemented")
    }

    override suspend fun signOut(): Result<Unit> {
        Logger.authFirebase("Firebase: signOut aún no implementado")
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): Result<User?> {
        Logger.authFirebase("Firebase: getCurrentUser aún no implementado")
        TODO("Not yet implemented")
    }
}