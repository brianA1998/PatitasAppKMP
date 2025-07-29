package com.patitasapp.kmp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.patitasapp.kmp.authentication.data.repository.AuthRepositoryImpl
import com.patitasapp.kmp.authentication.data.source.FirebaseAuthDataSource
import com.patitasapp.kmp.authentication.domain.usecase.SignInUseCase
import com.patitasapp.kmp.authentication.presentation.login.LoginScreen
import com.patitasapp.kmp.authentication.presentation.login.LoginViewModel
import com.patitasapp.kmp.core.utils.createDataStore
import com.patitasapp.kmp.onboarding.data.OnboardingRepositoryImpl
import com.patitasapp.kmp.onboarding.domain.usecase.CompleteOnboardingUseCase
import com.patitasapp.kmp.onboarding.domain.usecase.HasSeenOnboardingUseCase
import com.patitasapp.kmp.onboarding.presentation.OnboardingScreen
import com.patitasapp.kmp.onboarding.presentation.OnboardingViewModel

@Composable
fun NavigationHost(
    startDestination: NavigationRoute = NavigationRoute.Onboarding,
    modifier: Modifier = Modifier
){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {
        composable(NavigationRoute.Onboarding.route) {
            // Instancia del repositorio multiplataforma (DataStore iOS/Android)
            val repo = remember { OnboardingRepositoryImpl(createDataStore()) }

            val hasSeenUseCase = remember { HasSeenOnboardingUseCase(repo) }
            val completeUseCase = remember { CompleteOnboardingUseCase(repo) }
            val viewModel = remember { OnboardingViewModel(hasSeenUseCase, completeUseCase) }

            OnboardingScreen(
                viewModel = viewModel,
                onFinish = {
                    navController.navigate(NavigationRoute.Login.route) {
                        popUpTo(NavigationRoute.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }
        composable(NavigationRoute.Login.route) {
            val repo = remember { AuthRepositoryImpl(FirebaseAuthDataSource()) }
            val signInUseCase = remember { SignInUseCase(repo) }
            val loginVM = remember { LoginViewModel(signInUseCase) }

            LoginScreen(
                viewModel = loginVM,
                onLoginSuccess = {
                    navController.popBackStack()
                    // navController.navigate(NavigationRoute.Home.route)
                }
            )
        }

    }
}