package com.patitasapp.kmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.patitasapp.kmp.authentication.data.repository.AuthRepositoryImpl
import com.patitasapp.kmp.authentication.data.source.FirebaseAuthDataSource
import com.patitasapp.kmp.authentication.domain.usecase.SignInUseCase
import com.patitasapp.kmp.authentication.presentation.login.LoginViewModel
import com.patitasapp.kmp.navigation.AppNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    val authDataSource = remember { FirebaseAuthDataSource() }
    val authRepository = remember { AuthRepositoryImpl(authDataSource) }
    val signInUseCase = remember { SignInUseCase(authRepository) }
    val loginViewModel = remember { LoginViewModel(signInUseCase) }

    MaterialTheme {
        AppNavHost(
            navController = navController,
            loginViewModel = loginViewModel
        )
    }
}