package com.patitasapp.kmp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.patitasapp.kmp.authentication.domain.usecase.SignInUseCase
import com.patitasapp.kmp.authentication.presentation.login.LoginScreen
import com.patitasapp.kmp.authentication.presentation.login.LoginViewModel
import com.patitasapp.kmp.onboarding.domain.repository.OnboardingRepository
import com.patitasapp.kmp.onboarding.domain.usecase.CompleteOnboardingUseCase
import com.patitasapp.kmp.onboarding.domain.usecase.HasSeenOnboardingUseCase
import com.patitasapp.kmp.onboarding.presentation.OnboardingScreen
import com.patitasapp.kmp.onboarding.presentation.OnboardingViewModel

@Composable
fun NavigationHost(
    navController: NavHostController = rememberNavController(),
    createOnboardingRepo: () -> OnboardingRepository,
    createAuthDeps: () -> Pair<SignInUseCase, LoginViewModel>
) {
    NavHost(navController = navController, startDestination = NavigationRoute.Onboarding.route) {
        composable(NavigationRoute.Onboarding.route) {
            val repo = remember { createOnboardingRepo() }
            val hasSeenUseCase = remember { HasSeenOnboardingUseCase(repo) }
            val completeUseCase = remember { CompleteOnboardingUseCase(repo) }
            val viewModel = remember { OnboardingViewModel(hasSeenUseCase, completeUseCase) }
            OnboardingScreen(viewModel = viewModel, onFinish = {
                navController.navigate(NavigationRoute.Login.route) {
                    popUpTo(NavigationRoute.Onboarding.route) { inclusive = true }
                }
            })
        }
        composable(NavigationRoute.Login.route) {
            val (signInUse, vm) = remember { createAuthDeps() }
            LoginScreen(viewModel = vm, onLoginSuccess = {
                navController.popBackStack()
                // navController.navigate(NavigationRoute.Home.route)
            })
        }
    }
}