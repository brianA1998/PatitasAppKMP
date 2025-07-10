package com.patitasapp.kmp.authentication.presentation.login

import com.patitasapp.kmp.authentication.domain.model.User

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = false,
    val user: User? = null
)
