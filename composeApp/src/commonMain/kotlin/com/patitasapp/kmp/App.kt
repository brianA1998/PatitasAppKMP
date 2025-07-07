package com.patitasapp.kmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.patitasapp.kmp.authentication.data.repository.AuthRepositoryImpl
import com.patitasapp.kmp.authentication.data.source.FirebaseAuthDataSource
import com.patitasapp.kmp.authentication.domain.usecase.SignInUseCase
import com.patitasapp.kmp.authentication.presentation.LoginScreen
import com.patitasapp.kmp.authentication.presentation.LoginViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import patitasappkmp.composeapp.generated.resources.Res
import patitasappkmp.composeapp.generated.resources.compose_multiplatform

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