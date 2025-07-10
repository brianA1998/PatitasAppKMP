package com.patitasapp.kmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.patitasapp.kmp.authentication.data.repository.AuthRepositoryImpl
import com.patitasapp.kmp.authentication.data.source.FirebaseAuthDataSource
import com.patitasapp.kmp.authentication.domain.usecase.SignInUseCase
import com.patitasapp.kmp.authentication.presentation.login.LoginScreen
import com.patitasapp.kmp.authentication.presentation.login.LoginViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val authDataSource = remember { FirebaseAuthDataSource() }
    val authRepository = remember { AuthRepositoryImpl(authDataSource) }
    val signInUseCase = remember { SignInUseCase(authRepository) }
    val viewModel = remember { LoginViewModel(signInUseCase) }

    MaterialTheme {
        LoginScreen(
            viewModel = viewModel,
            onLoginSuccess = {
                // Aquí manejar la navegación a la siguiente pantalla
                println("Login exitoso!")
                // En el futuro, aquí implementarías navegación
            }
        )
    }
}