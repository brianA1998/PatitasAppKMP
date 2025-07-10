package com.patitasapp.kmp.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patitasapp.kmp.authentication.domain.usecase.SignInUseCase
import com.patitasapp.kmp.core.utils.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                Logger.authPresentation("Email actualizado: ${event.email}")
                _uiState.update { it.copy(email = event.email) }
            }
            is LoginEvent.PasswordChanged -> {
                Logger.authPresentation("Contraseña actualizada")
                _uiState.update { it.copy(password = event.password) }
            }
            is LoginEvent.Login -> {
                Logger.authPresentation("Iniciando proceso de login")
                signIn()
            }
        }
    }

    private fun signIn() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        Logger.authPresentation("Estado actualizado: cargando=true, error=null")

        viewModelScope.launch {
            val currentState = _uiState.value
            Logger.authPresentation("Invocando SignInUseCase con email: ${currentState.email}")

            signInUseCase(currentState.email, currentState.password)
                .onSuccess { user ->
                    Logger.authPresentation("Login exitoso para usuario: ${user.email}, uid=${user.uid}")
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isLoggedIn = true,
                            user = user
                        )
                    }
                    Logger.authPresentation("Estado actualizado: isLoggedIn=true, isLoading=false")
                }
                .onFailure { error ->
                    Logger.authPresentation("Error en login: ${error.message}")
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message ?: "Error desconocido"
                        )
                    }
                    Logger.authPresentation("Estado actualizado: isLoading=false, errorMessage=${error.message}")
                }
        }
    }
}